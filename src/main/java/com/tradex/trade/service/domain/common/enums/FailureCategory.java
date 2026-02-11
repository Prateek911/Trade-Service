package com.tradex.trade.service.domain.common.enums;

public enum FailureCategory implements Enumerable {

    VALIDATION("Validation"),
    TECHNICAL("Technical"),
    EXTERNAL_SERVICE("External Service"),
    TERMINAL("Terminal"),
    BUSINESS_RULE("Business Rule");

    private final String description;

    FailureCategory(String description) {
        this.description = description;
    }

    @Override
    public String description() {
        return description;
    }
}
