package com.tradex.trade.service.infrastructure.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradex.trade.service.domain.event.IDomainEvent;
import com.tradex.trade.service.infrastructure.messaging.topic.TopicResolver;
import com.tradex.trade.service.infrastructure.persistence.outbox.OutboxEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EventMapper {

    private final ObjectMapper objectMapper;
    private final TopicResolver topicResolver;


    public OutboxEntity toOutbox(IDomainEvent event) {

        try {

            String topic =
                    topicResolver.resolve(event.getClass().getSimpleName());

            String payloadJson =
                    objectMapper.writeValueAsString(event);

            return OutboxEntity.create(
                    event.getAggregateId(),
                    event.getClass().getSimpleName(),
                    event.getEventVersion(),
                    topic,
                    payloadJson
            );

        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize domain event", e);
        }
    }

}
