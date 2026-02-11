package com.tradex.trade.service.domain.allocation;

import java.math.BigDecimal;

public record PlannedAllocation(String organizationId, AllocationBasis basis, BigDecimal value) {

}
