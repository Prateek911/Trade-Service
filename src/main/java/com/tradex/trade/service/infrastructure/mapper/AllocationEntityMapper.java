package com.tradex.trade.service.infrastructure.mapper;

import com.tradex.trade.service.infrastructure.persistence.allocation.AllocationLegEntity;
import com.tradex.trade.service.infrastructure.persistence.allocation.TradeAllocationEntity;
import com.tradex.trade.service.domain.allocation.TradeAllocationState;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllocationEntityMapper {

    public TradeAllocationEntity toEntity(TradeAllocationState domain) {
        TradeAllocationEntity entity = new TradeAllocationEntity();
        entity.setTradeExecutionId(domain.tradeExecutionId);
        entity.setStatus(domain.status());
        entity.setRuleCode(domain.ruleCode());

        if (domain.allocationResult() != null) {
            List<AllocationLegEntity> legs =
                    domain.allocationResult()
                            .legs()
                            .stream()
                            .map(leg ->
                                    new AllocationLegEntity(
                                            entity,
                                            domain.getAggregateId(),
                                            leg.organizationId(),
                                            leg.allocatedQuantity(),
                                            leg.allocatedNotional(),
                                            leg.currency()
                                    )
                            )
                            .toList();

            entity.replaceLegs(legs);
        }

        return entity;
    }

}
