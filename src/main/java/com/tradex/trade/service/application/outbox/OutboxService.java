package com.tradex.trade.service.application.outbox;

import com.tradex.trade.service.infrastructure.mapper.EventMapper;
import com.tradex.trade.service.infrastructure.persistence.outbox.OutboxEntity;
import com.tradex.trade.service.domain.event.IDomainEvent;
import com.tradex.trade.service.domain.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class OutboxService {

    private final OutboxRepository outboxRepository;
    private final EventMapper eventMapper;

    @Transactional
    public void enqueue(IDomainEvent event) {

        OutboxEntity outboxEvent =
                eventMapper.toOutbox(event);

        outboxRepository.save(outboxEvent);
    }

    }
