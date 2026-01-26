package com.tradex.trade.service.application.service;

import com.tradex.trade.service.domain.event.IDomainEvent;
import org.springframework.stereotype.Service;

@Service
public interface IOutboxService {

    void enqueue(IDomainEvent event);
}
