package com.tradex.trade.service.domain.view;

import com.tradex.trade.service.domain.common.enums.Status;

import java.math.BigDecimal;

public interface AllocationView {

    String getTradeExecutionId();
    Status getStatus();
    String getRuleCode();
    String getOrganizationId();
    BigDecimal getAllocatedQuantity();
    BigDecimal getAllocatedNotional();
    String getCurrency();
}

