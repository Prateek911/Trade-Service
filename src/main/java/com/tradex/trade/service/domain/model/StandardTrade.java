package com.tradex.trade.service.domain.model;

import com.tradex.trade.service.domain.common.enums.TradeSide;
import com.tradex.trade.service.domain.common.interfaces.AggregateRoot;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
public class StandardTrade extends Entity implements AggregateRoot<Long> {

    private String tradeExecutionId;
    private String tradeId;
    private String instrumentId;
    private TradeSide side;
    private BigDecimal quantity;
    private BigDecimal price;
    private String currency;
    private Instant executionTimestamp;
    private String sourceSystem;
    private Map<String, Object> eventPayload;
}
