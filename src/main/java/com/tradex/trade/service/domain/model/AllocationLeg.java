package com.tradex.trade.service.domain.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
@Data
public class AllocationLeg {
    String accountId;
    BigDecimal quantity;
    Long legalEntity;
    Long book;
}
