package com.tradex.trade.service.infrastructure.persistence.impl;

import com.tradex.trade.service.domain.model.TradeAllocationRetry;
import com.tradex.trade.service.domain.repository.TradeRetryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TradeRetryRepositoryImpl implements TradeRetryRepository {
    @Override
    public TradeAllocationRetry findById(Long aLong) {
        return null;
    }

    @Override
    public TradeAllocationRetry save(TradeAllocationRetry aggregate) {
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }
}
