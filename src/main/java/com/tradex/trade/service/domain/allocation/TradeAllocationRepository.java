package com.tradex.trade.service.domain.allocation;

import com.tradex.trade.service.domain.view.AllocationView;
import com.tradex.trade.service.domain.common.repository.IRepository;
import com.tradex.trade.service.infrastructure.persistence.allocation.TradeAllocationEntity;
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
