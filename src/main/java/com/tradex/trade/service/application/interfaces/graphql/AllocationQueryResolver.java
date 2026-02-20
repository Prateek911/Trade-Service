package com.tradex.trade.service.application.interfaces.graphql;

import com.tradex.trade.service.domain.view.AllocationView;
import com.tradex.trade.service.domain.allocation.AllocationLeg;
import com.tradex.trade.service.domain.allocation.TradeAllocation;
import com.tradex.trade.service.domain.allocation.TradeAllocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AllocationQueryResolver {

    private final TradeAllocationRepository repository;

    @QueryMapping
    public TradeAllocation allocationByTrade(
            @Argument String tradeExecutionId
    ) {

        List<AllocationView> rows =
                repository.findAllocationView(tradeExecutionId);

        if (rows.isEmpty()) {
            return null;
        }

        AllocationView first = rows.get(0);

        return TradeAllocation.builder()
                .tradeExecutionId(first.getTradeExecutionId())
                .status(first.getStatus())
                .ruleCode(first.getRuleCode())
                .legs(rows.stream()
                        .map(r ->
                                new AllocationLeg(
                                        r.getOrganizationId(),
                                        r.getAllocatedQuantity(),
                                        r.getAllocatedNotional(),
                                        r.getCurrency()
                                )
                        )
                        .toList()
                )
                .build();
    }
}
