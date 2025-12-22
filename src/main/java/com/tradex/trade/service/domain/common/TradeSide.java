package com.tradex.trade.service.domain.common;

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
