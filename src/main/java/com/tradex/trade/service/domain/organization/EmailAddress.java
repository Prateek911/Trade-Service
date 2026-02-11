package com.tradex.trade.service.domain.organization;

import com.tradex.trade.service.domain.common.AggregateRoot;
import com.tradex.trade.service.domain.entity.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class EmailAddress extends Entity implements AggregateRoot<Long> {

    private String email;
    private String type;
}
