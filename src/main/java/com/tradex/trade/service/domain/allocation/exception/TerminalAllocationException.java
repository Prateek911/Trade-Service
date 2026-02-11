package com.tradex.trade.service.domain.allocation.exception;

import lombok.Getter;

@Getter
public class TerminalAllocationException extends RuntimeException {

        private final String failureCode;

        public TerminalAllocationException(String failureCode, String message) {
            super(message);
            this.failureCode = failureCode;
        }

}

