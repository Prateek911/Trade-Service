package com.tradex.trade.service.domain.event;

import java.time.Instant;

public abstract class BaseDomainEvent implements IDomainEvent {

    private final Instant occurredAt;

    protected BaseDomainEvent(Instant occurredAt) {
        this.occurredAt = Instant.now();
    }

    @Override
    public Instant occurredAt() {
        return occurredAt;
    }
}
