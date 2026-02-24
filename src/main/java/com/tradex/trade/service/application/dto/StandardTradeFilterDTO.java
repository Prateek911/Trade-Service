package com.tradex.trade.service.application.dto;

import com.tradex.trade.service.domain.trade.TradeSide;
import com.tradex.trade.service.infrastructure.persistence.dto.FilterDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StandardTradeFilterDTO extends FilterDTO {

    private String tradeExecutionId;
    private String instrumentId;
    private TradeSide side;
    private String currency;
    private String sourceSystem;
    private Long id;

    private Order orderId;
    private Order orderSourceSystem;
    private Order orderSide;
    private Order orderInstrumentId;
    private Order orderTradeExecutionId;
    private Order orderCurrency;

}
