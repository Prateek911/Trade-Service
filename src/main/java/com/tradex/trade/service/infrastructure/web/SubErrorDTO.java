package com.tradex.trade.service.infrastructure.web;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SubErrorDTO {

    private final String field;
    private final Object rejectedValue;
    private final String message;

}
