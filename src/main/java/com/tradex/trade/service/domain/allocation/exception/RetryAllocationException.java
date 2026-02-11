package com.tradex.trade.service.domain.allocation.exception;

import lombok.Getter;

@Getter
public class RetryAllocationException extends RuntimeException {
    private final String failureCode;

    public RetryAllocationException(String failureCode, String message) {
        super(message);
        this.failureCode = failureCode;
    }

}
