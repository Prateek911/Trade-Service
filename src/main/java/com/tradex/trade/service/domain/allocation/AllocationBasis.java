package com.tradex.trade.service.domain.allocation;

import com.tradex.trade.service.domain.common.enums.Enumerable;

public enum AllocationBasis implements Enumerable {
    PRO_RATA("PRO_RATA"),
    EQUAL_SHARE("EQUAL_SHARE"),
    PRIORITY_BASED("PRIORITY_BASED"),
    FIXED_QUANTITY("FIXED_QUANTITY"),
    PERCENTAGE("PERCENTAGE"),
    WEIGHT("WEIGHT");

    private final String description;

    AllocationBasis(String description) {
        this.description = description;
    }

    @Override
    public String description() {
        return description;
    }
}
