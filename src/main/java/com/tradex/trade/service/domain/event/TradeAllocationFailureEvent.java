package com.tradex.trade.service.domain.event;

import com.tradex.trade.service.domain.common.enums.FailureCategory;

import java.time.Instant;

public final class TradeAllocationFailureEvent extends BaseDomainEvent {

    private final String tradeExecutionId;
    private final FailureCategory failureCategory;
    private final String failureCode;
    private final String failureMessage;
    private final boolean retryable;

    public TradeAllocationFailureEvent(String tradeExecutionId, FailureCategory failureCategory,
                                       String failureCode, String failureMessage, boolean retryable, Instant occurredAt)
    {
        super(occurredAt);
        this.tradeExecutionId = tradeExecutionId;
        this.failureCategory = failureCategory;
        this.failureCode = failureCode;
        this.failureMessage = failureMessage;
        this.retryable = retryable;

    }

    public String tradeExecutionId() {
        return tradeExecutionId;
    }

    public FailureCategory failureCategory() {
        return failureCategory;
    }

    public String failureCode() {
        return failureCode;
    }

    public String failureMessage() {
        return failureMessage;
    }

    public boolean retryable() {
        return retryable;
    }
}
