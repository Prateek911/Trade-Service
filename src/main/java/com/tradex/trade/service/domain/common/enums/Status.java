package com.tradex.trade.service.domain.common.enums;

import com.tradex.trade.service.domain.common.interfaces.Enumerable;

public enum Status implements Enumerable {

    ALLOCATED("Allocated"),
    PENDING("Pending"),
    REJECTED("Rejected"),
    SETTLED("Settled"),
    CANCELLED("Cancelled"),
    FAILED("Failed"),
    AWAITING_RETRY("Awaiting  Retry");

    private final String description;

    Status(String description) {this.description = description;}

    @Override
    public String description() {
        return description;
    }
}
