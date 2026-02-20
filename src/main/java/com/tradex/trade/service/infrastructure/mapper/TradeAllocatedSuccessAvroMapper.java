package com.tradex.trade.service.infrastructure.mapper;

import com.tradex.trade.service.domain.allocation.TradeAllocation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TradeAllocatedSuccessAvroMapper extends AvroMapper {

    @Mapping(target = "eventVersion", constant = "1")
    @Mapping(target = "occurredAt", expression = "java(currentTimestamp())")
    com.tradex.trade.service.events.TradeAllocationSuccess toAvro(TradeAllocation allocation);

    List<com.tradex.trade.service.events.AllocationLeg> toAvroLegs(
            List<com.tradex.trade.service.domain.allocation.AllocationLeg> legs
    );

    @Mapping(target = "allocatedQuantity",
            expression = "java(leg.allocatedQuantity() == null ? 0d : leg.allocatedQuantity().doubleValue())")
    @Mapping(target = "allocatedNotional",
            expression = "java(leg.allocatedNotional() == null ? 0d : leg.allocatedNotional().doubleValue())")
    com.tradex.trade.service.events.AllocationLeg toAvro(
            com.tradex.trade.service.domain.allocation.AllocationLeg leg
    );
}
