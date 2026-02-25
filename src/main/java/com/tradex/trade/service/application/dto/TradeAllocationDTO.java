package com.tradex.trade.service.application.dto;

import com.tradex.trade.service.domain.allocation.AllocationLeg;
import com.tradex.trade.service.domain.common.enums.Status;
import com.tradex.trade.service.infrastructure.persistence.dto.EntityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TradeAllocationDTO extends EntityDTO {

    private String tradeExecutionId;
    private Status status;
    private String ruleCode;
    private List<AllocationLeg> legs;

}
