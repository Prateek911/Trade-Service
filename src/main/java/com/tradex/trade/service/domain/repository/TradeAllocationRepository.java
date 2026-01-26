package com.tradex.trade.service.domain.repository;

import com.tradex.trade.service.application.view.AllocationView;
import com.tradex.trade.service.domain.common.interfaces.IRepository;
import com.tradex.trade.service.domain.entity.TradeAllocationEntity;
import com.tradex.trade.service.domain.model.TradeAllocation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TradeAllocationRepository extends IRepository<TradeAllocation,Long> {
    TradeAllocation save(TradeAllocation allocation);
    TradeAllocationEntity save(TradeAllocationEntity allocation);
    List<AllocationView> findAllocationView(String tradeExecutionId);
    Optional<TradeAllocationEntity> findByTradeExecutionId(String tradeExecutionId);

}
