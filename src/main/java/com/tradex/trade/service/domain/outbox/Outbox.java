package com.tradex.trade.service.domain.outbox;

import com.tradex.trade.service.application.outbox.OutboxStatus;
import com.tradex.trade.service.domain.common.AggregateRoot;
import com.tradex.trade.service.domain.entity.Entity;
import com.tradex.trade.service.infrastructure.messaging.kafka.EventEnvelope;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Outbox extends Entity implements AggregateRoot<Long> {

    private String eventId;
    private String aggregateId;
    private String eventType;
    private int eventVersion;
    private String topic;
    private EventEnvelope<?> payload;
    private OutboxStatus status;
    private Instant publishedAt;
}
