package com.tradex.trade.service.infrastructure.messaging.kafka;

import org.springframework.stereotype.Component;

@Component
public class TopicResolver {

    public String resolve(String eventType) {
        return switch (eventType) {
            case "TradeAllocated" -> "trades.allocated";
            case "TradeAllocationFailed" -> "trades.allocation.failed";
            default ->
                    throw new IllegalArgumentException(
                            "No topic mapping for event type " + eventType
                    );
        };
    }

}
