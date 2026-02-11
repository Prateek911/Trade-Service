package com.tradex.trade.service.domain.allocation;

import com.tradex.trade.service.domain.common.enums.Status;
import com.tradex.trade.service.domain.common.AggregateRoot;
import com.tradex.trade.service.domain.entity.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
public class TradeAllocation extends Entity implements AggregateRoot<Long> {

    private String tradeExecutionId;
    private Status status;
    private String ruleCode;
    private List<AllocationLeg> legs;
}
