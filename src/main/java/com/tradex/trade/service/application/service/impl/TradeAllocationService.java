package com.tradex.trade.service.application.service.impl;

import com.tradex.trade.service.application.service.IAllocationRule;
import com.tradex.trade.service.application.service.IOutboxService;
import com.tradex.trade.service.application.service.ITradeAllocationService;
import com.tradex.trade.service.domain.common.exception.RetryAllocationException;
import com.tradex.trade.service.domain.common.exception.TerminalAllocationException;
import com.tradex.trade.service.domain.entity.TradeAllocationFailureEntity;
import com.tradex.trade.service.domain.entity.TradeAllocationRetryEntity;
import com.tradex.trade.service.domain.model.Organization;
import com.tradex.trade.service.domain.model.StandardTrade;
import com.tradex.trade.service.domain.registry.AllocationRuleRegistry;
import com.tradex.trade.service.domain.repository.*;
import com.tradex.trade.service.domain.state.IAllocationEngine;
import com.tradex.trade.service.domain.state.TradeAllocationState;
import com.tradex.trade.service.infrastructure.mapper.AllocationDomainMapper;
import com.tradex.trade.service.infrastructure.mapper.AllocationEntityMapper;
import com.tradex.trade.service.infrastructure.mapper.TradeAllocationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class TradeAllocationService implements ITradeAllocationService {

    private final IOutboxService outboxService;
    private final StandardTradeRepository standardTradeRepository;
    private final TradeAllocationRepository tradeAllocationRepository;
    private final OrganizationRepository organizationRepository;

    private final AllocationRuleRegistry ruleRegistry;
    private final IAllocationEngine allocationEngine;

    private final AllocationEntityMapper entityMapper;
    private final AllocationDomainMapper domainMapper;

    private final TradeRetryRepository retryRepository;
    private final TradeFailureRepository failureRepository;


    @Override
    @Transactional
    public void allocate(String tradeExecutionId) {

        StandardTrade trade = standardTradeRepository.findByTradeExecutionId(tradeExecutionId);

        TradeAllocationState allocationState =
                tradeAllocationRepository
                        .findByTradeExecutionId(tradeExecutionId)
                        .map(domainMapper::toDomain)
                        .orElseGet(() -> new TradeAllocationState(tradeExecutionId));;



        List<Organization> eligibleOrganizations =
                organizationRepository.findEligibleForAllocation(trade);

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
            persistAllocation(allocationState);
            retryRepository.upsert(
                    TradeAllocationRetryEntity.from(
                            tradeExecutionId,
                            ex
                    )
            );
            publishDomain(allocationState);
        }catch (TerminalAllocationException ex) {

            persistAllocation(allocationState);

            failureRepository.save(
                    TradeAllocationFailureEntity.from(
                            tradeExecutionId,
                            ex
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
