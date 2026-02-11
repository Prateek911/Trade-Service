package com.tradex.trade.service.infrastructure.persistence.allocation;

import com.tradex.trade.service.domain.view.AllocationView;
import com.tradex.trade.service.domain.exception.RecordNotFoundException;
import com.tradex.trade.service.domain.allocation.TradeAllocation;
import com.tradex.trade.service.domain.allocation.TradeAllocationRepository;
import com.tradex.trade.service.infrastructure.mapper.TradeAllocationMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TradeAllocationRepositoryImpl implements TradeAllocationRepository {

    private final JpaTradeAllocationRepository repository;
    private final TradeAllocationMapper mapper;

    @Override
    public TradeAllocation findById(Long id) {
        return mapper.toModel(repository.findById(id)
                .orElseThrow(()-> new RecordNotFoundException(TradeAllocation.class,"id"))
        );
    }

    @Override
    public TradeAllocation save(TradeAllocation allocation) {
        var model= mapper.toEntity(allocation);
        return mapper.toModel(repository.save(model));
    }

    @Override
    public TradeAllocationEntity save(TradeAllocationEntity allocation) {
        return repository.save(allocation);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public List<AllocationView> findAllocationView(String tradeExecutionId) {
        return repository.findAllocationView(tradeExecutionId);
    }

    @Override
    public Optional<TradeAllocationEntity> findByTradeExecutionId(String tradeExecutionId) {
        return repository.findByTradeExecutionId(tradeExecutionId);
    }

}
