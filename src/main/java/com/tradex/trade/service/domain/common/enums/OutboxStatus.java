package com.tradex.trade.service.domain.common.enums;

import com.tradex.trade.service.domain.common.interfaces.Enumerable;

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
