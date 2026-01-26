package com.tradex.trade.service.application.service;

import com.tradex.trade.service.domain.common.exception.RetryAllocationException;
import com.tradex.trade.service.domain.common.exception.TerminalAllocationException;
import com.tradex.trade.service.domain.model.AllocationLeg;
import com.tradex.trade.service.domain.model.AllocationPlan;
import com.tradex.trade.service.domain.model.Organization;
import com.tradex.trade.service.domain.model.StandardTrade;

import java.util.List;

public interface IAllocationRule {

    String ruleCode();

    boolean supports(StandardTrade trade);

    AllocationPlan plan(StandardTrade trade, List<Organization> eligibleOrganizations) throws RetryAllocationException, TerminalAllocationException;
}
