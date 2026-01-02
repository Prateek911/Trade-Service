package com.tradex.trade.service.domain.common;

public enum OutboxStatus implements Enumerable{

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
