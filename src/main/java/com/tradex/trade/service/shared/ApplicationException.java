package com.tradex.trade.service.shared;

import com.tradex.trade.service.infrastructure.web.ErrorCode;
import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

    private final ErrorCode errorCode;

    public ApplicationException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}
