package com.tradex.trade.service.domain.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

public record AllocationLeg(
        String organizationId,
        BigDecimal allocatedQuantity,
        BigDecimal allocatedNotional,
        String currency
) {}
