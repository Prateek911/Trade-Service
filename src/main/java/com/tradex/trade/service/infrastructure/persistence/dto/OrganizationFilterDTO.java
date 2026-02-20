package com.tradex.trade.service.infrastructure.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationFilterDTO extends FilterDTO{

    private String sourceId;
    private String sourceName;
    private String exchangeId;
    private String status;
    private String regNumber;
    private String shortName;
    private String longName;
    private Long id;

    private Order orderId;
    private Order orderSourceId;
    private Order orderSourceName;
    private Order orderShortName;
    private Order orderLongName;
    private Order orderRegNumber;
    private Order orderExchangeId;
    private Order orderStatus;

}
