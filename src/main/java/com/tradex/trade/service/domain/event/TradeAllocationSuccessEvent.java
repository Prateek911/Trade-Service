package com.tradex.trade.service.domain.event;

import com.tradex.trade.service.domain.allocation.AllocationLeg;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
@AllArgsConstructor
public final class TradeAllocationSuccessEvent extends BaseDomainEvent {

    private final String tradeExecutionId;
    private final List<AllocationLeg> allocations;
    private final String ruleCode;
    private final Instant occurredAt;

    public String tradeExecutionId() {
        return tradeExecutionId;
    }

    public List<AllocationLeg> allocations() {
        return allocations;
    }

    public String ruleCode() {
        return ruleCode;
    }

    @Override
    public String getAggregateId() {
        return tradeExecutionId;
    }

    @Override
    public int getEventVersion() {
        return 1;
    }
}
