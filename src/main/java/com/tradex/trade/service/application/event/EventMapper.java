package com.tradex.trade.service.application.event;

import com.tradex.trade.service.domain.event.IDomainEvent;
import com.tradex.trade.service.domain.event.TradeAllocationFailureEvent;
import com.tradex.trade.service.domain.event.TradeAllocationSuccessEvent;
import com.tradex.trade.service.infrastructure.messaging.kafka.EventEnvelope;

public class EventMapper {

    public static EventEnvelope<?> toEnvelope(IDomainEvent event) {

        if (event instanceof TradeAllocationSuccessEvent e) {
            return EventEnvelope.of(
                    "TradeAllocationSuccess",
                    1,
                    "trade-processing-service",
                    e.tradeExecutionId(),
                    e
            );
        }

        if (event instanceof TradeAllocationFailureEvent e) {
            return EventEnvelope.of(
                    "TradeAllocationFailed",
                    1,
                    "trade-processing-service",
                    e.tradeExecutionId(),
                    e
            );
        }
        throw new IllegalArgumentException("Unknown domain event: " + event);

    }

}
