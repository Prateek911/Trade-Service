package com.tradex.trade.service.application.service.impl;

import com.tradex.trade.service.application.service.ITradeAllocationService;
import com.tradex.trade.service.application.service.ITradeRetryService;
import com.tradex.trade.service.domain.common.exception.RetryAllocationException;
import com.tradex.trade.service.domain.common.exception.TerminalAllocationException;
import com.tradex.trade.service.domain.entity.TradeAllocationRetryEntity;
import com.tradex.trade.service.domain.model.StandardTrade;
import com.tradex.trade.service.domain.model.TradeAllocationRetry;
import com.tradex.trade.service.domain.repository.StandardTradeRepository;
import com.tradex.trade.service.domain.repository.TradeFailureRepository;
import com.tradex.trade.service.domain.repository.TradeRetryRepository;
import com.tradex.trade.service.domain.repository.TradeAllocationRepository;
import com.tradex.trade.service.infrastructure.mapper.TradeRetryMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

@Slf4j
@RequiredArgsConstructor
public class TradeRetryService implements ITradeRetryService {

    private final StandardTradeRepository standardTradeRepository;
    private final ITradeAllocationService tradeAllocationService;
    private final TradeAllocationRepository successRepository;
    private final TradeRetryRepository retryRepository;
    private final TradeFailureRepository failureRepository;
    private final TradeRetryMapper retryMapper;

    @Override
    @Transactional
    public void retry(TradeAllocationRetry retry) {

        TradeAllocationRetryEntity retryEntity = retryMapper.toEntity(retry);

        StandardTrade trade = standardTradeRepository.findByTradeExecutionId(retry.getTradeExecutionId());


        try {
            tradeAllocationService.allocate(trade.getTradeExecutionId());
            //retryRepository.delete(retry);

        } catch (RetryAllocationException e) {
         retryEntity.setRetryCount(retry.getRetryCount() + 1);
         retryEntity.setNextAttemptAt(computeNextAttempt(retry.getRetryCount()));
         retryEntity.setLastFailureCode(e.getFailureCode());
        } catch (TerminalAllocationException e) {
           //move to failure repository
            //retryRepository.delete(retry);
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
