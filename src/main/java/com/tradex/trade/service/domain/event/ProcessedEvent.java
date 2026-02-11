package com.tradex.trade.service.domain.event;

import com.tradex.trade.service.domain.common.AggregateRoot;
import com.tradex.trade.service.domain.entity.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ProcessedEvent extends Entity implements AggregateRoot<Long> {

    private String eventId;
    private Instant processedAt;
}
