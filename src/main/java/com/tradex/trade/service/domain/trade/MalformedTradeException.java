package com.tradex.trade.service.domain.trade;

import lombok.Getter;

@Getter
public class MalformedTradeException extends RuntimeException {

    public MalformedTradeException(String message) {
        super(message);
    }
}
