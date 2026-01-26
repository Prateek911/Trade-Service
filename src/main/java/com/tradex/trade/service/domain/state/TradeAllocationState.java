package com.tradex.trade.service.domain.state;

import com.tradex.trade.service.application.service.IAllocationRule;
import com.tradex.trade.service.domain.common.enums.FailureCategory;
import com.tradex.trade.service.domain.common.enums.Status;
import com.tradex.trade.service.domain.common.exception.RetryAllocationException;
import com.tradex.trade.service.domain.common.exception.TerminalAllocationException;
import com.tradex.trade.service.domain.common.interfaces.AggregateRoot;
import com.tradex.trade.service.domain.event.IDomainEvent;
import com.tradex.trade.service.domain.event.TradeAllocationFailureEvent;
import com.tradex.trade.service.domain.event.TradeAllocationSuccessEvent;
import com.tradex.trade.service.domain.model.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class TradeAllocationState implements AggregateRoot<String> {

    private final String tradeExecutionId;
    private Status status;
    private String ruleCode;
    private AllocationResult allocationResult;

    private final List<IDomainEvent> domainEvents = new ArrayList<>();

    public TradeAllocationState(String tradeExecutionId) {
        this.tradeExecutionId = tradeExecutionId;
        this.status = Status.ALLOCATED;
    }

    @Override
    public String getId() {
        return tradeExecutionId;
    }

    public Status status() {
        return status;
    }

    public String ruleCode() {
        return ruleCode;
    }


    public List<IDomainEvent> pullDomainEvents() {
        List<IDomainEvent> events = List.copyOf(domainEvents);
        domainEvents.clear();
        return events;
    }

    private void ensureState(Status... allowed){
        for (Status s : allowed) {
            if (this.status == s) {
                return;
            }
        }
        throw new IllegalStateException(
                "Invalid allocation transition from " + status
        );
    }

    public void restore(
            Status status,
            String ruleCode,
            AllocationResult allocationResult
    ) {
        this.status = status;
        this.ruleCode = ruleCode;
        this.allocationResult = allocationResult;
    }

    private void transitionToAllocating(){
            this.status=Status.ALLOCATING;
    }

    public void allocate(StandardTrade trade, IAllocationRule rule, IAllocationEngine engine, List<Organization> organizations) {

        ensureState(Status.RECEIVED,Status.RETRY);

        transitionToAllocating();

        try{
            AllocationPlan plan = rule.plan(trade, organizations);
            AllocationResult result = engine.allocate(trade,plan);
            applySuccess(plan,result);

        } catch (RetryAllocationException e) {
            applyRetryFailure(e);
            throw e;
        } catch (TerminalAllocationException ex) {
            applyTerminalFailure(ex);
            throw ex;
        }
    }

    private void applyRetryFailure(RetryAllocationException ex){
        this.status=Status.RETRY;
        domainEvents.add(new TradeAllocationFailureEvent(
                tradeExecutionId,
                FailureCategory.BUSINESS_RULE,
                ex.getFailureCode(),
                ex.getMessage(),
                true,
                Instant.now()
        )
        );
    }

    private void applyTerminalFailure(TerminalAllocationException ex){
        this.status=Status.FAILED;
        domainEvents.add(new TradeAllocationFailureEvent(
                        tradeExecutionId,
                        FailureCategory.VALIDATION,
                        ex.getFailureCode(),
                        ex.getMessage(),
                        false,
                        Instant.now()
                )
        );
    }



    private void applySuccess(AllocationPlan allocationPlan, AllocationResult allocationResult){
        this.allocationResult = allocationResult;
        this.ruleCode=allocationPlan.ruleCode();
        this.status=Status.ALLOCATED;

        domainEvents.add(
                new TradeAllocationSuccessEvent(
                        tradeExecutionId,
                        allocationResult.legs(),
                        allocationPlan.ruleCode(),
                        Instant.now()
                )
        );

    }
}
