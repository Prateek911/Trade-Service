package com.tradex.trade.service.application.service;

import com.tradex.trade.service.domain.model.TradeAllocationRetry;
import org.springframework.stereotype.Service;

@Service
public interface ITradeRetryService {
    void retry(TradeAllocationRetry retry);
}
