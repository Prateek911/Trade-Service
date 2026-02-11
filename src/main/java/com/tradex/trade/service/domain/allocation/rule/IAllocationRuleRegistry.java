package com.tradex.trade.service.domain.allocation.rule;

import com.tradex.trade.service.domain.allocation.exception.TerminalAllocationException;
import com.tradex.trade.service.domain.trade.StandardTrade;

public interface IAllocationRuleRegistry {

    IAllocationRule resolve(StandardTrade trade) throws TerminalAllocationException;
}
