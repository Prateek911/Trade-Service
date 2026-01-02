package com.tradex.trade.service.domain.event;

import java.time.Instant;

public interface IDomainEvent {
    Instant occurredAt();
}
