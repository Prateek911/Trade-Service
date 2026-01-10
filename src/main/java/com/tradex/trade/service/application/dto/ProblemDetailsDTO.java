package com.tradex.trade.service.application.dto;

//RFC-7807 compliant

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.net.URI;
import java.time.Instant;
import java.util.List;

@Getter
@AllArgsConstructor
public class ProblemDetailsDTO {

    private final URI type;
    private final String title;
    private final int status;
    private final String detail;
    private final URI instance;
    private final String errorCode;
    private final String correlationId;
    private final Instant timestamp;
    private final List<SubErrorDTO> subErrors;
}
