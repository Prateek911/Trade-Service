package com.tradex.trade.service.application.view;

import com.tradex.trade.service.domain.common.enums.Status;

import java.math.BigDecimal;

public interface AllocationView {

    String getTradeExecutionId();
    Status getStatus();
    String getRuleCode();
    String getRuleVersion();
    String getOrganizationId();
    BigDecimal getAllocatedQuantity();
    BigDecimal getAllocatedNotional();
    String getCurrency();
}

