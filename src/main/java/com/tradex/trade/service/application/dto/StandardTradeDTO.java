package com.tradex.trade.service.application.dto;

import com.tradex.trade.service.domain.trade.TradeSide;
import com.tradex.trade.service.infrastructure.persistence.dto.EntityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class StandardTradeDTO extends ClientDTO {

    private String tradeExecutionId;
    private String instrumentId;
    private TradeSide side;
    private BigDecimal quantity;
    private BigDecimal price;
    private String currency;
    private String sourceSystem;
    private Instant tradeTimestamp;
}
