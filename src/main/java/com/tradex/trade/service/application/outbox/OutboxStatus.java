package com.tradex.trade.service.application.outbox;

import com.tradex.trade.service.domain.common.enums.Enumerable;

public enum OutboxStatus implements Enumerable {

    NEW("New"),
    PUBLISHED("Published");

    private final String description;

    OutboxStatus(String description){
        this.description = description;
    }

    @Override
    public String description(){
        return description;
    }
}
