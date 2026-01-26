package com.tradex.trade.service.infrastructure.persistence.impl;

import com.tradex.trade.service.domain.model.TradeAllocationFailure;
import com.tradex.trade.service.domain.repository.TradeFailureRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TradeFailureRepositoryImpl implements TradeFailureRepository {
    @Override
    public TradeAllocationFailure findById(Long aLong) {
        return null;
    }

    public TradeAllocationFailure save(TradeAllocationFailure aggregate) {
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }
}
