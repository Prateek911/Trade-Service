package com.tradex.trade.service.domain.allocation;

import com.tradex.trade.service.domain.common.enums.FailureCategory;
import com.tradex.trade.service.domain.common.AggregateRoot;
import com.tradex.trade.service.domain.entity.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class TradeAllocationFailure extends Entity implements AggregateRoot<Long> {

    private String tradeExecutionId;
    private FailureCategory failureCategory;
    private String failureCode;
    private String failureMessage;
    private boolean retryable;
    private Instant failedAt;
}
