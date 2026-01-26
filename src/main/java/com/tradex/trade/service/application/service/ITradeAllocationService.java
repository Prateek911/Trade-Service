package com.tradex.trade.service.application.service;

import org.springframework.stereotype.Service;

@Service
public interface ITradeAllocationService {
    void allocate(String tradeExecutionId);
}
