package com.tradex.trade.service.infrastructure.persistence.failure;

import com.tradex.trade.service.infrastructure.persistence.allocation.TradeAllocationFailureEntity;
import com.tradex.trade.service.domain.allocation.TradeAllocationFailure;
import com.tradex.trade.service.domain.allocation.TradeFailureRepository;
import com.tradex.trade.service.infrastructure.mapper.TradeFailureMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TradeFailureRepositoryImpl implements TradeFailureRepository {

    private final JpaTradeFailureRepository repository;
    private final TradeFailureMapper mapper;

    @Override
    public TradeAllocationFailure findById(Long aLong) {
        return null;
    }

    @Override
    public TradeAllocationFailure save(TradeAllocationFailureEntity entity) {
        return mapper.toModel(repository.save(entity));
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
