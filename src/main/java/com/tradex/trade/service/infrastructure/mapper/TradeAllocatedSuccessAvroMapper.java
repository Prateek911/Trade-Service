package com.tradex.trade.service.infrastructure.mapper;

import com.tradex.trade.service.domain.allocation.TradeAllocation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring",uses=AvroMapper.class)
public interface TradeAllocatedSuccessAvroMapper {

    @Mapping(target = "eventVersion", constant = "1")
    @Mapping(target = "occurredAt", expression = "java(currentTimestamp())")
    com.tradex.trade.service.events.TradeAllocationSuccess toAvro(TradeAllocation allocation);

    List<com.tradex.trade.service.events.AllocationLeg> toAvroLegs(
            List<com.tradex.trade.service.domain.allocation.AllocationLeg> legs
    );

    @Mapping(target = "allocatedQuantity",
            expression = "java(leg.getAllocatedQuantity() != null ? leg.getAllocatedQuantity().doubleValue() : null)")
    @Mapping(target = "allocatedNotional",
            expression = "java(leg.allocatedNotional() != null ? leg.allocatedNotional().doubleValue() : null)")
    com.tradex.trade.service.events.AllocationLeg toAvro(
            com.tradex.trade.service.domain.allocation.AllocationLeg leg
    );
}
