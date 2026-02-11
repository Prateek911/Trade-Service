package com.tradex.trade.service.infrastructure.persistence.trade;

import com.tradex.trade.service.domain.exception.RecordNotFoundException;
import com.tradex.trade.service.domain.trade.StandardTrade;
import com.tradex.trade.service.domain.repository.StandardTradeRepository;
import com.tradex.trade.service.infrastructure.mapper.StandardTradeMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class StandardTradeRepositoryImpl implements StandardTradeRepository {

    private final JpaStandardTradeRepository repository;
    private final StandardTradeMapper mapper;

    @Override
    public StandardTrade findById(Long id) {
        return mapper.toModel(repository.findById(id).orElseThrow(()-> new RecordNotFoundException(StandardTradeEntity.class,id)));
    }


    @Override
    public StandardTrade save(StandardTradeEntity entity) {
        return mapper.toModel(repository.save(entity));
    }

    @Override
    public Boolean existsByTradeExecutionId(String tradeExecutionId) {
        return repository.existsByTradeExecutionId(tradeExecutionId);
    }

    @Override
    public List<StandardTradeEntity> lockPendingTrades(int batchSize) {
        return  repository.lockPendingTrades(batchSize);
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
