package com.tradex.trade.service.domain.event;

import com.tradex.trade.service.domain.model.AllocationLeg;

import java.time.Instant;
import java.util.List;

public final class TradeAllocationSuccessEvent extends BaseDomainEvent {

    private final String tradeExecutionId;
    private final List<AllocationLeg> allocations;
    private final String ruleVersion;

    public TradeAllocationSuccessEvent(String tradeExecutionId, List<AllocationLeg> allocations,
                                       String ruleVersion) {
        super();
        this.tradeExecutionId = tradeExecutionId;
        this.allocations = List.copyOf(allocations);
        this.ruleVersion = ruleVersion;
    }

    public String tradeExecutionId() {
        return tradeExecutionId;
    }

    public List<AllocationLeg> allocations() {
        return allocations;
    }

    public String ruleVersion() {
        return ruleVersion;
    }

}
