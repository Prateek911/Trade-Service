package com.tradex.trade.service.domain.trade;

import com.tradex.trade.service.domain.common.AggregateRoot;
import com.tradex.trade.service.domain.entity.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
public class StandardTrade extends Entity implements AggregateRoot<Long> {

    private String tradeExecutionId;
    private String instrumentId;
    private TradeSide side;
    private BigDecimal quantity;
    private BigDecimal price;
    private String currency;
    private String sourceSystem;
    private Instant tradeTimestamp;
}
