package com.tradex.trade.service.domain.common.enums;

import com.tradex.trade.service.domain.common.interfaces.Enumerable;

public enum TradeSide implements Enumerable {

    BUY("Buy"),
    SELL("Sell");

    private final String description;

    TradeSide(String description) {
        this.description = description;
    }

    @Override
    public String description() {
        return description;
    }
}
