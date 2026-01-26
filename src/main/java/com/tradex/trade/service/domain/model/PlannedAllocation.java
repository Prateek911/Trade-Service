package com.tradex.trade.service.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
public class PlannedAllocation {

    private final String organizationId;
    private final AllocationBasis basis;
    private final BigDecimal value;


}
