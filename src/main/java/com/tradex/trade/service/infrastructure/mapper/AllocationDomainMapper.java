package com.tradex.trade.service.infrastructure.mapper;

import com.tradex.trade.service.infrastructure.persistence.allocation.TradeAllocationEntity;
import com.tradex.trade.service.domain.allocation.AllocationLeg;
import com.tradex.trade.service.domain.allocation.AllocationResult;
import com.tradex.trade.service.domain.allocation.TradeAllocationState;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllocationDomainMapper {

    public TradeAllocationState toDomain(
            TradeAllocationEntity entity
    ) {
        TradeAllocationState allocation =
                new TradeAllocationState(entity.getTradeExecutionId());

        allocation.restore(
                entity.getStatus(),
                entity.getRuleCode(),
                mapResult(entity)
        );

        return allocation;
    }

    private AllocationResult mapResult(
            TradeAllocationEntity entity
    ) {
        List<AllocationLeg> legs =
                entity.getLegs().stream()
                        .map(l ->
                                new AllocationLeg(
                                        l.getOrganizationId(),
                                        l.getAllocatedQuantity(),
                                        l.getAllocatedNotional(),
                                        l.getCurrency()
                                )
                        )
                        .toList();

        return new AllocationResult(legs);
    }

}
