package com.tradex.trade.service.infrastructure.persistence.allocation;

import com.tradex.trade.service.application.dto.TradeAllocationFilterDTO;
import com.tradex.trade.service.domain.view.AllocationView;
import com.tradex.trade.service.domain.exception.RecordNotFoundException;
import com.tradex.trade.service.domain.allocation.TradeAllocation;
import com.tradex.trade.service.domain.allocation.TradeAllocationRepository;
import com.tradex.trade.service.infrastructure.mapper.TradeAllocationMapper;
import com.tradex.trade.service.infrastructure.pagination.PageableBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TradeAllocationRepositoryImpl implements TradeAllocationRepository {

    private final JpaTradeAllocationRepository repository;
    private final TradeAllocationMapper mapper;
    private final int maxPageSize;

    public TradeAllocationRepositoryImpl(
            JpaTradeAllocationRepository repository,
            TradeAllocationMapper mapper,
            @Value("${service.query.size:100}") int maxPageSize
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.maxPageSize = maxPageSize;
    }

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

    @Override
    public Page<TradeAllocationEntity> findAll(TradeAllocationFilterDTO dto) {
        Specification<TradeAllocationEntity> spec = AllocationSpecification.filter(dto);
        Pageable pageable = PageableBuilder.tradeAllocationBuilder(dto, maxPageSize);
        return repository.findAll(spec, pageable);
    }

}
