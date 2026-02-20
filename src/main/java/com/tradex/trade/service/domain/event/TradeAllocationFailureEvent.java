package com.tradex.trade.service.domain.event;

import com.tradex.trade.service.domain.common.enums.FailureCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public final class TradeAllocationFailureEvent extends BaseDomainEvent {

    private final String tradeExecutionId;
    private final FailureCategory failureCategory;
    private final String failureCode;
    private final String failureMessage;
    private final boolean retryable;
    private final Instant occurredAt;

    public String tradeExecutionId() {
        return tradeExecutionId;
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
