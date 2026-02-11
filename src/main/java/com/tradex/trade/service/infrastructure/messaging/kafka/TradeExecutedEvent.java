package com.tradex.trade.service.infrastructure.messaging.kafka;

import java.math.BigDecimal;
import java.time.Instant;

public record TradeExecutedEvent(
        String tradeExecutionId,
        String instrumentId,
        String side,
        BigDecimal quantity,
        BigDecimal price,
        String currency,
        BigDecimal fxRate,
        Instant fxTimestamp,
        Instant tradeTimestamp,
        String sourceSystem,
        String ruleCode,
        String ruleVersion
) {}
