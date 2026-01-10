package com.tradex.trade.service.domain.model;

import com.tradex.trade.service.domain.common.enums.Status;
import com.tradex.trade.service.domain.common.interfaces.AggregateRoot;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.List;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
public class TradeAllocation extends Entity implements AggregateRoot<Long> {

    private String tradeExecutionId;
    private Status status;
    private List<AllocationLeg> allocationResult;
    private Instant allocatedAt;
}
