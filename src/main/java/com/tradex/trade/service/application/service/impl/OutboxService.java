package com.tradex.trade.service.application.service.impl;

import com.tradex.trade.service.application.event.EventMapper;
import com.tradex.trade.service.application.service.IOutboxService;
import com.tradex.trade.service.domain.entity.OutboxEntity;
import com.tradex.trade.service.domain.event.IDomainEvent;
import com.tradex.trade.service.domain.repository.OutboxRepository;
import com.tradex.trade.service.infrastructure.messaging.kafka.EventEnvelope;
import com.tradex.trade.service.infrastructure.messaging.kafka.TopicResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
public class OutboxService implements IOutboxService {

    private final OutboxRepository outboxRepository;
    private final TopicResolver topicResolver;

    @Override
    @Transactional
    public void enqueue(IDomainEvent event) {

        EventEnvelope<?> envelope =
                EventMapper.toEnvelope(event);

        String topic =
                topicResolver.resolve(envelope.eventType());

        OutboxEntity outboxEvent =
                OutboxEntity.fromEnvelope(
                        envelope,
                        topic
                );

        outboxRepository.save(outboxEvent);
    }

    }
