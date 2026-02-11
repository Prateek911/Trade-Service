package com.tradex.trade.service.domain.allocation;

import com.tradex.trade.service.domain.allocation.exception.TerminalAllocationException;
import com.tradex.trade.service.domain.trade.StandardTrade;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class AllocationEngine {

    public AllocationResult allocate(StandardTrade trade, AllocationPlan plan) {
        return switch (plan.basis()){
            case PRO_RATA -> null;
            case EQUAL_SHARE -> null;
            case PRIORITY_BASED -> null;
            case FIXED_QUANTITY -> allocateFixed(trade, plan);
            case PERCENTAGE -> null;
            case WEIGHT -> null;
        };
    }

    private AllocationResult allocateFixed(StandardTrade trade, AllocationPlan plan) {

        BigDecimal total = plan.allocations().stream()
                .map(PlannedAllocation::value)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (total.compareTo(trade.getQuantity())!=0) {
            throw new TerminalAllocationException(
                    "QTY_MISMATCH",
                    "Fixed quantity allocation does not match trade quantity"
            );
        }

        return buildResult(trade, plan.allocations());

    }

    private AllocationResult buildResult(
            StandardTrade trade,
            List<PlannedAllocation> allocations
    ){

        List<AllocationLeg> legs = allocations.stream()
                .map(p->buildLeg(trade, p.organizationId(), p.value()))
                .toList();

        return new AllocationResult(legs);

    }

    private AllocationLeg buildLeg(
            StandardTrade trade,
            String orgId,
            BigDecimal qty
    ){
        return new AllocationLeg(orgId,
                qty,
                qty.multiply(trade.getPrice()),
                trade.getCurrency()
        );
    }


}
