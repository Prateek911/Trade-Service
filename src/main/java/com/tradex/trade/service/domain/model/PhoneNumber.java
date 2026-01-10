package com.tradex.trade.service.domain.model;

import com.tradex.trade.service.domain.common.interfaces.AggregateRoot;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class PhoneNumber extends Entity implements AggregateRoot<Long> {

    private String phoneNumber;
    private String label;

}
