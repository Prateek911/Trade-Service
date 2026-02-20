package com.tradex.trade.service.infrastructure.mapper;

import com.tradex.trade.service.domain.allocation.TradeAllocationFailure;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TradeAllocationFailedAvroMapper extends AvroMapper {

    @Mapping(target = "failureCategory", expression = "java(allocationFailure.getFailureCategory().name())")
    @Mapping(target = "failedAt", expression = "java(currentTimestamp())")
    com.tradex.trade.service.events.AllocationFailed toAvro(TradeAllocationFailure allocationFailure);


}
