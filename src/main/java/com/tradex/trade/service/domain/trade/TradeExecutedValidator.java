package com.tradex.trade.service.domain.trade;

import com.tradex.trade.service.infrastructure.messaging.kafka.TradeExecutedEvent;
import org.springframework.stereotype.Component;

@Component
public class TradeExecutedValidator {

    public void validate(TradeExecutedEvent event) {

        if (event.tradeExecutionId() == null || event.tradeExecutionId().isBlank()) {
            throw new MalformedTradeException("Missing tradeExecutionId");
        }

        if (event.quantity() == null || event.quantity().signum() <= 0) {
            throw new MalformedTradeException("Invalid quantity");
        }

        if (event.price() == null || event.price().signum() <= 0) {
            throw new MalformedTradeException("Invalid price");
        }

        if (event.currency() == null || event.currency().length() != 3) {
            throw new MalformedTradeException("Invalid currency");
        }

        if (event.tradeTimestamp() == null) {
            throw new MalformedTradeException("Missing execution time");
        }
    }
}
