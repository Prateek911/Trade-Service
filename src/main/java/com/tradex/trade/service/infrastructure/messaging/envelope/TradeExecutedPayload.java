package com.tradex.trade.service.infrastructure.messaging.envelope;

import com.tradex.trade.service.domain.event.IDomainEvent;

import java.time.Instant;

public record TradeExecutedPayload(String tradeExecutionId) implements IDomainEvent {
    @Override
    public Instant occurredAt() {
        return Instant.now();
    }
}
