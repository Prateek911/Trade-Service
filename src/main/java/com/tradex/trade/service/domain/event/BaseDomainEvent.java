package com.tradex.trade.service.domain.event;

import java.time.Instant;

public abstract class BaseDomainEvent implements IDomainEvent {

    private final Instant occurredAt;

    protected BaseDomainEvent() {
        this.occurredAt = Instant.now();
    }

    @Override
    public Instant getOccurredAt() {
        return occurredAt;
    }
}
