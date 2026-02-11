package com.tradex.trade.service.infrastructure.mapper;

import com.tradex.trade.service.domain.trade.TradeSide;
import com.tradex.trade.service.infrastructure.persistence.trade.StandardTradeEntity;
import com.tradex.trade.service.infrastructure.messaging.kafka.TradeExecutedEvent;
import org.springframework.stereotype.Component;

@Component
public class ConsumedTradeMapper {

    public StandardTradeEntity toEntity(
            TradeExecutedEvent event
    ) {
        return  StandardTradeEntity.builder()
                .tradeExecutionId(event.tradeExecutionId())
                .instrumentId(event.instrumentId())
                .side(TradeSide.valueOf(event.side()))
                .quantity(event.quantity())
                .price(event.price())
                .currency(event.currency())
                .fxRate(event.fxRate())
                .fxTimestamp(event.fxTimestamp())
                .sourceSystem(event.sourceSystem())
                .tradeTimestamp(event.tradeTimestamp())
                .build();
    }
}
