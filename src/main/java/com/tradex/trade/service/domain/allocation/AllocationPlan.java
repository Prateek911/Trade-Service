package com.tradex.trade.service.domain.allocation;

import java.util.List;

public final class AllocationPlan {

    private final String ruleCode;
    private final List<PlannedAllocation> allocations;

    public AllocationPlan(String ruleCode, List<PlannedAllocation> allocations) {
        this.ruleCode = ruleCode;
        this.allocations = List.copyOf(allocations);
        validate();
    }

    private void validate(){
        if(allocations.isEmpty()){
            throw new IllegalArgumentException("Allocation Plans cannot be empty");
        }

       AllocationBasis basis = allocations.get(0).basis();

        boolean mixed = allocations.stream().anyMatch(p -> !p.basis().equals(basis));

        if(mixed){
           throw new IllegalArgumentException("Allocation Plans cannot be mixed");
        }
    }

    public AllocationBasis basis(){
        return allocations.get(0).basis();
    }

    public List<PlannedAllocation> allocations(){
        return allocations;
    }

    public String ruleCode() {
        return ruleCode;
    }
}
