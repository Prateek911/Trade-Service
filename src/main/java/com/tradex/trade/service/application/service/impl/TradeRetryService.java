package com.tradex.trade.service.application.service.impl;

import com.tradex.trade.service.application.service.ITradeRetryService;
import com.tradex.trade.service.domain.model.StandardTrade;
import com.tradex.trade.service.domain.model.TradeAllocationRetry;
import com.tradex.trade.service.domain.repository.StandardTradeRepository;
import com.tradex.trade.service.domain.repository.TradeFailureRepository;
import com.tradex.trade.service.domain.repository.TradeRetryRepository;
import com.tradex.trade.service.domain.repository.TradeSuccessRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
public class TradeRetryService implements ITradeRetryService {

    private final StandardTradeRepository standardTradeRepository;
    private final TradeSuccessRepository successRepository;
    private final TradeRetryRepository retryRepository;
    private final TradeFailureRepository failureRepository;

    @Override
    @Transactional
    public void retry(TradeAllocationRetry retry) {

        StandardTrade trade = standardTradeRepository.findByTradeExecutionId(retry.getTradeExecutionId());

    }
}
