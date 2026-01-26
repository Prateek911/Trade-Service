package com.tradex.trade.service.domain.registry;

import com.tradex.trade.service.application.service.IAllocationRule;
import com.tradex.trade.service.domain.common.exception.TerminalAllocationException;
import com.tradex.trade.service.domain.model.StandardTrade;

public interface IAllocationRuleRegistry {

    IAllocationRule resolve(StandardTrade trade) throws TerminalAllocationException;
}
