package com.tradex.trade.service.infrastructure.persistence.impl;

import com.tradex.trade.service.domain.model.TradeAllocation;
import com.tradex.trade.service.domain.repository.TradeSuccessRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TradeSuccessRepositoryImpl implements TradeSuccessRepository {
    @Override
    public TradeAllocation findById(Long aLong) {
        return null;
    }

    @Override
    public TradeAllocation save(TradeAllocation aggregate) {
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }
}
