package com.tradex.trade.service.application.dto;

import com.tradex.trade.service.domain.common.enums.Status;
import com.tradex.trade.service.infrastructure.persistence.dto.FilterDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TradeAllocationFilterDTO extends FilterDTO {

    private String tradeExecutionId;
    private Status status;
    private String ruleCode;
    private Long id;

    private Order orderId;
    private Order orderTradeExecutionId;
    private Order orderStatus;
    private Order orderRuleCode;


}
