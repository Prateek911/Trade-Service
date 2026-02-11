package com.tradex.trade.service.infrastructure.persistence.outbox;

import com.tradex.trade.service.application.outbox.OutboxStatus;
import com.tradex.trade.service.infrastructure.persistence.Persistable;
import com.tradex.trade.service.infrastructure.messaging.kafka.EventEnvelope;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.Instant;

@EnableJpaAuditing
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "outbox",
indexes = {
        @Index(name = "idx_outbox_status_created_at", columnList = "status, created_at"),
        @Index(name = "idx_outbox_status_published_at", columnList = "status, published_at")
})
public class OutboxEntity extends Persistable {

    @Column(name = "event_id", nullable = false, updatable = false)
    private String eventId;

    @Column(name = "aggregate_id", nullable = false, updatable = false)
    private String aggregateId;

    @Column(name = "event_type", nullable = false, updatable = false)
    private String eventType;

    @Column(name = "event_version", nullable = false, updatable = false)
    private int eventVersion;

    @Column(name = "topic", nullable = false, updatable = false)
    private String topic;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "payload", columnDefinition = "jsonb", nullable = false, updatable = false)
    private EventEnvelope<?> payload;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OutboxStatus status = OutboxStatus.NEW;

    @Column(name = "published_at")
    private Instant publishedAt;

    public static OutboxEntity fromEnvelope(
            EventEnvelope<?> envelope,
            String topic
    ) {
        return OutboxEntity.builder()
                .eventId(envelope.eventId())
                .aggregateId(envelope.aggregateId())
                .eventType(envelope.eventType())
                .eventVersion(envelope.eventVersion())
                .topic(topic)
                .payload(envelope)
                .status(OutboxStatus.NEW)
                .build();
    }

    public void markPublished() {
        this.status = OutboxStatus.PUBLISHED;
        this.publishedAt = Instant.now();
    }
}
