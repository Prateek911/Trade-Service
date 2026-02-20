package com.tradex.trade.service.domain.common.enums;

public enum Status implements Enumerable {

    ALLOCATED("Allocated"),
    ALLOCATING("Allocating"),
    PENDING("Pending"),
    RECEIVED("Received"),
    REJECTED("Rejected"),
    SETTLED("Settled"),
    CANCELLED("Cancelled"),
    FAILED("Failed"),
    ACTIVE("Active"),
    RETRY("Retry");

    private final String description;

    Status(String description) {this.description = description;}

    @Override
    public String description() {
        return description;
    }
}
