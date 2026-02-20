package com.tradex.trade.service.application.allocation;

import com.tradex.trade.service.domain.allocation.*;
import com.tradex.trade.service.application.outbox.OutboxService;
import com.tradex.trade.service.domain.allocation.rule.AllocationRuleRegistry;
import com.tradex.trade.service.domain.allocation.rule.IAllocationRule;
import com.tradex.trade.service.domain.common.enums.FailureCategory;
import com.tradex.trade.service.domain.allocation.exception.RetryAllocationException;
import com.tradex.trade.service.domain.allocation.exception.TerminalAllocationException;
import com.tradex.trade.service.infrastructure.persistence.allocation.TradeAllocationFailureEntity;
import com.tradex.trade.service.domain.organization.Organization;
import com.tradex.trade.service.domain.trade.StandardTrade;
import com.tradex.trade.service.domain.repository.*;
import com.tradex.trade.service.infrastructure.mapper.AllocationDomainMapper;
import com.tradex.trade.service.infrastructure.mapper.AllocationEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TradeAllocationService {

    private final OutboxService outboxService;
    private final StandardTradeRepository standardTradeRepository;
    private final TradeAllocationRepository tradeAllocationRepository;
    private final OrganizationRepository organizationRepository;

    private final AllocationRuleRegistry ruleRegistry;
    private final AllocationEngine allocationEngine;

    private final AllocationEntityMapper entityMapper;
    private final AllocationDomainMapper domainMapper;

    private final TradeRetryRepository retryRepository;
    private final TradeFailureRepository failureRepository;


    @Transactional
    public void allocate(String tradeExecutionId) {

        StandardTrade trade = standardTradeRepository.findByTradeExecutionId(tradeExecutionId);

        TradeAllocationState allocationState =
                tradeAllocationRepository
                        .findByTradeExecutionId(tradeExecutionId)
                        .map(domainMapper::toDomain)
                        .orElseGet(() -> new TradeAllocationState(tradeExecutionId));



        List<Organization> eligibleOrganizations =
                organizationRepository.findEligibleForAllocation(trade.getSourceSystem());

        try {
            IAllocationRule rule = ruleRegistry.resolve(trade);

            allocationState.allocate(
                    trade,
                    rule,
                    allocationEngine,
                    eligibleOrganizations
            );

            persistAllocation(allocationState);

            retryRepository.deleteByTradeExecutionId(tradeExecutionId);

            publishDomain(allocationState);

        } catch(RetryAllocationException rex){
            retryRepository.upsertRetry(
                    trade.getTradeExecutionId(),
                    Instant.now(),
                    rex.getFailureCode()
            );

            publishDomain(allocationState);

        }catch (TerminalAllocationException ex) {

            failureRepository.save(
                    TradeAllocationFailureEntity.from(
                            tradeExecutionId,
                            FailureCategory.TERMINAL,
                            ex.getFailureCode(),
                            ex.getMessage(),
                            false
                    )
            );

            retryRepository.deleteByTradeExecutionId(tradeExecutionId);

            publishDomain(allocationState);
        }

    }

    private void publishDomain(TradeAllocationState allocation) {

        allocation.pullDomainEvents().forEach(outboxService::enqueue);

    }

    private void persistAllocation(TradeAllocationState allocation) {
        tradeAllocationRepository.save(entityMapper.toEntity(allocation));
    }


}
