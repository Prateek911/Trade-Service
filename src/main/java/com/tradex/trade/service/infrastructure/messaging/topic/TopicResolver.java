package com.tradex.trade.service.infrastructure.messaging.topic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TopicResolver {

    @Value("${kafka.topics.trades-allocated}")
    private String tradesAllocatedTopic;

    @Value("${kafka.topics.trades-allocation-failed}")
    private String tradesAllocationFailedTopic;

    public String resolve(String eventType) {
        return switch (eventType) {
            case "TradeAllocated" -> tradesAllocatedTopic;
            case "TradeAllocationFailed" -> tradesAllocationFailedTopic;
            default ->
                    throw new IllegalArgumentException(
                            "No topic mapping for event type " + eventType
                    );
        };
    }

}
