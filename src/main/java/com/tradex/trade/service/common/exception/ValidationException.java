package com.tradex.trade.service.common.exception;

import com.tradex.trade.service.api.dto.SubErrorDTO;
import com.tradex.trade.service.domain.common.ErrorCode;

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
