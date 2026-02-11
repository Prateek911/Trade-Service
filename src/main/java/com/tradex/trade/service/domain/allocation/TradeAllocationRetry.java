package com.tradex.trade.service.domain.allocation;

import com.tradex.trade.service.domain.common.AggregateRoot;
import com.tradex.trade.service.domain.entity.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class TradeAllocationRetry extends Entity implements AggregateRoot<Long> {

    private String tradeExecutionId;
    private int retryCount;
    private int maxRetries;
    private Instant nextAttemptAt;
    private String lastFailureCode;

}
