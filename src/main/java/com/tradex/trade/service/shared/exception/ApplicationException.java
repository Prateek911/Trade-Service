package com.tradex.trade.service.shared.exception;

import com.tradex.trade.service.infrastructure.web.ErrorCode;

public class ApplicationException extends RuntimeException {

    private final ErrorCode errorCode;

    public ApplicationException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {return errorCode;}
}
