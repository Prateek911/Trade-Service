package com.tradex.trade.service.domain.common.exception;

public class TerminalAllocationException extends RuntimeException {

        private final String failureCode;

        public TerminalAllocationException(String failureCode, String message) {
            super(message);
            this.failureCode = failureCode;
        }

        public String getFailureCode() {
            return failureCode;
        }
    }

