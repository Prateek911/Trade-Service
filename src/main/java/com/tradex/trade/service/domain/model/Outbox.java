package com.tradex.trade.service.domain.model;

import com.tradex.trade.service.domain.common.enums.OutboxStatus;
import com.tradex.trade.service.domain.common.interfaces.AggregateRoot;
import com.tradex.trade.service.infrastructure.messaging.kafka.EventEnvelope;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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
