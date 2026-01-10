package com.tradex.trade.service.domain.repository;

import com.tradex.trade.service.domain.common.interfaces.IRepository;
import com.tradex.trade.service.domain.entity.StandardTradeEntity;
import com.tradex.trade.service.domain.model.StandardTrade;

public interface StandardTradeRepository extends IRepository<StandardTrade, Long> {
    StandardTrade findByTradeExecutionId(String tradeExecutionId);
}
