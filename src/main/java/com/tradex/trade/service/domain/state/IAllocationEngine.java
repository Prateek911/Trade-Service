package com.tradex.trade.service.domain.state;

import com.tradex.trade.service.domain.model.AllocationPlan;
import com.tradex.trade.service.domain.model.AllocationResult;
import com.tradex.trade.service.domain.model.StandardTrade;

public interface IAllocationEngine {

    AllocationResult allocate(StandardTrade trade, AllocationPlan plan);
}
