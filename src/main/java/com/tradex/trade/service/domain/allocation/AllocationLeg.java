package com.tradex.trade.service.domain.allocation;

import java.math.BigDecimal;

public record AllocationLeg(
        String organizationId,
        BigDecimal allocatedQuantity,
        BigDecimal allocatedNotional,
        String currency
) {}
