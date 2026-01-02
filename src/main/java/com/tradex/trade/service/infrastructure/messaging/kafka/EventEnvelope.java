package com.tradex.trade.service.infrastructure.messaging.kafka;


import com.tradex.trade.service.domain.event.IDomainEvent;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;


public final class EventEnvelope<T extends IDomainEvent> {

    private final String eventId;
    private final String eventType;
    private final int eventVersion;
    private final Instant occurredAt;
    private final Instant publishedAt;
    private final String source;
    private final String aggregateId;
    private final T payload;
    private final Map<String, String> headers;


    private EventEnvelope(String eventId, String eventType, int eventVersion, Instant occurredAt,
                          Instant publishedAt, String source, String aggregateId, T payload, Map<String, String> headers) {
        this.eventId = eventId;
        this.eventType = eventType;
        this.eventVersion = eventVersion;
        this.occurredAt = occurredAt;
        this.publishedAt = publishedAt;
        this.source = source;
        this.aggregateId = aggregateId;
        this.payload = payload;
        this.headers = headers;
    }

    public static<T extends IDomainEvent> EventEnvelope<T> of( String eventType,
                                                              int eventVersion,
                                                              String source,
                                                              String aggregateId,
                                                              T payload) {
        return new EventEnvelope<>(UUID.randomUUID().toString(),
                eventType,
                eventVersion,
                payload.occurredAt(),
                Instant.now(),
                source,
                aggregateId,
                payload,
                Map.of());
    }

    public String eventId() { return eventId; }
    public String eventType() { return eventType; }
    public int eventVersion() { return eventVersion; }
    public Instant occurredAt() { return occurredAt; }
    public Instant publishedAt() { return publishedAt; }
    public String source() { return source; }
    public String aggregateId() { return aggregateId; }
    public T payload() { return payload; }
    public Map<String, String> headers() { return headers; }


}
