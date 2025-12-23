package com.tradex.trade.service.common.exception;

import com.tradex.trade.service.domain.common.ErrorCode;

public class ApplicationException extends RuntimeException {

    private final ErrorCode errorCode;

    public ApplicationException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {return errorCode;}
}
