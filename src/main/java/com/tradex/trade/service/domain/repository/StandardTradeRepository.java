package com.tradex.trade.service.domain.repository;

import com.tradex.trade.service.domain.common.repository.IRepository;
import com.tradex.trade.service.infrastructure.persistence.trade.StandardTradeEntity;
import com.tradex.trade.service.domain.trade.StandardTrade;

import java.util.List;

public interface StandardTradeRepository extends IRepository<StandardTrade, Long> {
    StandardTrade findByTradeExecutionId(String tradeExecutionId);
    StandardTrade save(StandardTradeEntity standardTrade);
    Boolean existsByTradeExecutionId(String tradeExecutionId);
    List<StandardTradeEntity> lockPendingTrades(int batchSize);
}
