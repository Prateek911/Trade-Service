package com.tradex.trade.service.infrastructure.mapper;

import com.tradex.trade.service.infrastructure.persistence.allocation.TradeAllocationEntity;
import com.tradex.trade.service.domain.allocation.TradeAllocation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = DateMapper.class)
public interface TradeAllocationMapper {

    TradeAllocation toModel(TradeAllocationEntity entity);
    TradeAllocationEntity toEntity(TradeAllocation model);
    List<TradeAllocation> toModels(List<TradeAllocationEntity> entities);
}
