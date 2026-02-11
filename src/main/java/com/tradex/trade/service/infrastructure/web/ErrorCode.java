package com.tradex.trade.service.infrastructure.web;

import com.tradex.trade.service.domain.common.enums.Enumerable;

public enum ErrorCode implements Enumerable {

    ERR_404_001("Record Not Found"),
    ERR_409_001("Record Already Exists"),
    ERR_400_001("Validation Failed"),
    ERR_422_001("Business Rule Violation"),
    ERR_503_001("External Service Unavailable"),
    ERR_500_001("Internal Server Error");

    private final String description;

    ErrorCode(String description) {
        this.description = description;
    }

    @Override
    public String description() {
        return this.description;
    }

}
