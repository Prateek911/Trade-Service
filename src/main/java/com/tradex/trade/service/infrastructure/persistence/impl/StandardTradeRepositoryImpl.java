package com.tradex.trade.service.infrastructure.persistence.impl;

import com.tradex.trade.service.domain.common.exception.RecordNotFoundException;
import com.tradex.trade.service.domain.entity.StandardTradeEntity;
import com.tradex.trade.service.domain.model.StandardTrade;
import com.tradex.trade.service.domain.repository.StandardTradeRepository;
import com.tradex.trade.service.infrastructure.mapper.StandardTradeMapper;
import com.tradex.trade.service.infrastructure.persistence.jpa.JpaStandardTradeRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class StandardTradeRepositoryImpl implements StandardTradeRepository {

    private final JpaStandardTradeRepository repository;
    private final StandardTradeMapper mapper;

    @Override
    public StandardTrade findById(Long id) {
        return mapper.toModel(repository.findById(id).orElseThrow(()-> new RecordNotFoundException(StandardTradeEntity.class,id)));
    }


    public StandardTrade save(StandardTradeEntity entity) {
        return mapper.toModel(repository.save(entity));
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public StandardTrade findByTradeExecutionId(String tradeExecutionId) {
        return mapper.toModel(repository.findByTradeExecutionId(tradeExecutionId).orElseThrow(()-> new RecordNotFoundException(StandardTradeEntity.class,"tradeExecutionId")));
    }
}
