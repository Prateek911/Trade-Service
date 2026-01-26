package com.tradex.trade.service.domain.common.exception;

public class RetryAllocationException extends RuntimeException {
    private final String failureCode;

    public RetryAllocationException(String failureCode, String message) {
        super(message);
        this.failureCode = failureCode;
    }

    public String getFailureCode() {
        return failureCode;
    }
}
