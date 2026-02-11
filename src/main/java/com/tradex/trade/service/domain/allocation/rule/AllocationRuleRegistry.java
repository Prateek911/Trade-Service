package com.tradex.trade.service.domain.allocation.rule;

import com.tradex.trade.service.domain.allocation.exception.TerminalAllocationException;
import com.tradex.trade.service.domain.trade.StandardTrade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class AllocationRuleRegistry implements IAllocationRuleRegistry {

    private final List<IAllocationRule> rules;

    @Override
    public IAllocationRule resolve(StandardTrade trade) throws TerminalAllocationException {
        return rules.stream()
                .filter(rule -> rule.supports(trade))
                .findFirst()
                .orElseThrow(() -> new TerminalAllocationException(
                        "RULE_NOT_FOUND",
                        "No allocation rule for instrument "
                                + trade.getInstrumentId()
                ));
    }
}
