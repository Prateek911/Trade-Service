package com.tradex.trade.service.infrastructure.messaging;

import com.tradex.trade.service.application.view.AllocationView;
import com.tradex.trade.service.domain.model.AllocationLeg;
import com.tradex.trade.service.domain.model.TradeAllocation;
import com.tradex.trade.service.domain.repository.TradeAllocationRepository;
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

        return new TradeAllocation(
                first.getTradeExecutionId(),
                first.getStatus().name(),
                first.getRuleCode(),
                first.getRuleVersion(),
                rows.stream()
                        .map(r ->
                                new AllocationLeg(
                                        r.getOrganizationId(),
                                        r.getAllocatedQuantity(),
                                        r.getAllocatedNotional(),
                                        r.getCurrency()
                                )
                        )
                        .toList()
        );
    }
}
