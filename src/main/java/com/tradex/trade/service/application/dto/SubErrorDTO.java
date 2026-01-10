package com.tradex.trade.service.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SubErrorDTO {

    private final String field;
    private final Object rejectedValue;
    private final String message;

}
