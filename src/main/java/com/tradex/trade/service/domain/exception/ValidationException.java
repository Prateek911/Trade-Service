package com.tradex.trade.service.domain.exception;

import com.tradex.trade.service.infrastructure.web.SubErrorDTO;
import com.tradex.trade.service.infrastructure.web.ErrorCode;
import com.tradex.trade.service.shared.ApplicationException;

import java.util.List;

public class ValidationException extends ApplicationException {

    private final List<SubErrorDTO> subErrors;

    public ValidationException(String message, List<SubErrorDTO> subErrors) {
        super(ErrorCode.ERR_400_001, message);
        this.subErrors = subErrors;
    }

    public List<SubErrorDTO> getSubErrors() {
        return subErrors;
    }

}
