package com.tradex.trade.service.application.retry;

import com.tradex.trade.service.application.allocation.TradeAllocationService;
import com.tradex.trade.service.domain.allocation.exception.RetryAllocationException;
import com.tradex.trade.service.domain.allocation.exception.TerminalAllocationException;
import com.tradex.trade.service.domain.common.enums.FailureCategory;
import com.tradex.trade.service.infrastructure.persistence.allocation.TradeAllocationFailureEntity;
import com.tradex.trade.service.infrastructure.persistence.retry.TradeAllocationRetryEntity;
import com.tradex.trade.service.domain.trade.StandardTrade;
import com.tradex.trade.service.domain.allocation.TradeAllocationRetry;
import com.tradex.trade.service.domain.repository.StandardTradeRepository;
import com.tradex.trade.service.domain.allocation.TradeFailureRepository;
import com.tradex.trade.service.domain.allocation.TradeRetryRepository;
import com.tradex.trade.service.infrastructure.mapper.TradeRetryMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@RequiredArgsConstructor
@Service
public class TradeRetryService {

    private final StandardTradeRepository standardTradeRepository;
    private final TradeAllocationService tradeAllocationService;
    private final TradeRetryRepository retryRepository;
    private final TradeFailureRepository failureRepository;
    private final TradeRetryMapper retryMapper;

    @Transactional
    public void retry(TradeAllocationRetry retry) {

        TradeAllocationRetryEntity retryEntity = retryMapper.toEntity(retry);

        StandardTrade trade = standardTradeRepository.findByTradeExecutionId(retry.getTradeExecutionId());


        try {
            tradeAllocationService.allocate(trade.getTradeExecutionId());
            retryRepository.deleteByTradeExecutionId(trade.getTradeExecutionId());

        } catch (RetryAllocationException e) {

         retryEntity.setRetryCount(retry.getRetryCount() + 1);
         retryEntity.setNextAttemptAt(computeNextAttempt(retry.getRetryCount()));
         retryEntity.setLastFailureCode(e.getFailureCode());

        } catch (TerminalAllocationException ex) {

            failureRepository.save(
                    TradeAllocationFailureEntity.from(
                            trade.getTradeExecutionId(),
                            FailureCategory.TERMINAL,
                            ex.getFailureCode(),
                            ex.getMessage(),
                            false
                    )
            );

            retryRepository.deleteByTradeExecutionId(trade.getTradeExecutionId());
        }

    }

    private Instant computeNextAttempt(int retryCount) {
        return switch (retryCount) {
            case 1 -> Instant.now().plusSeconds(60);
            case 2 -> Instant.now().plusSeconds(300);
            case 3 -> Instant.now().plusSeconds(900);
            default -> Instant.now().plusSeconds(3600);
        };
    }
}
