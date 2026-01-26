package com.tradex.trade.service.domain.common.enums;

import com.tradex.trade.service.domain.common.interfaces.Enumerable;

public enum Status implements Enumerable {

    ALLOCATED("Allocated"),
    ALLOCATING("Allocating"),
    RECEIVED("Received"),
    REJECTED("Rejected"),
    SETTLED("Settled"),
    CANCELLED("Cancelled"),
    FAILED("Failed"),
    RETRY("Retry");

    private final String description;

    Status(String description) {this.description = description;}

    @Override
    public String description() {
        return description;
    }
}
