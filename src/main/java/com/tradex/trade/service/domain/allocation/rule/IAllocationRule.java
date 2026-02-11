package com.tradex.trade.service.domain.allocation.rule;

import com.tradex.trade.service.domain.allocation.AllocationPlan;
import com.tradex.trade.service.domain.allocation.exception.RetryAllocationException;
import com.tradex.trade.service.domain.allocation.exception.TerminalAllocationException;
import com.tradex.trade.service.domain.organization.Organization;
import com.tradex.trade.service.domain.trade.StandardTrade;

import java.util.List;

public interface IAllocationRule {

    boolean supports(StandardTrade trade);

    AllocationPlan plan(StandardTrade trade, List<Organization> eligibleOrganizations) throws RetryAllocationException, TerminalAllocationException;
}
