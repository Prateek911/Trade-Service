package com.tradex.trade.service.domain.event;

import com.tradex.trade.service.domain.model.AllocationLeg;

import java.time.Instant;
import java.util.List;

public final class TradeAllocationSuccessEvent extends BaseDomainEvent {

    private final String tradeExecutionId;
    private final List<AllocationLeg> allocations;
    private final String ruleCode;

    public TradeAllocationSuccessEvent(String tradeExecutionId, List<AllocationLeg> allocations,
                                       String ruleCode, Instant occurredAt) {
        super(occurredAt);
        this.tradeExecutionId = tradeExecutionId;
        this.allocations = List.copyOf(allocations);
        this.ruleCode = ruleCode;
    }

    public String tradeExecutionId() {
        return tradeExecutionId;
    }

    public List<AllocationLeg> allocations() {
        return allocations;
    }

    public String ruleCode() {
        return ruleCode;
    }

}
