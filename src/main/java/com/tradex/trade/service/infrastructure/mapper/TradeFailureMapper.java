package com.tradex.trade.service.infrastructure.mapper;

import com.tradex.trade.service.infrastructure.persistence.allocation.TradeAllocationFailureEntity;
import com.tradex.trade.service.domain.allocation.TradeAllocationFailure;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = DateMapper.class)
public interface TradeFailureMapper {

    TradeAllocationFailure toModel(TradeAllocationFailureEntity model);
    TradeAllocationFailureEntity toEntity(TradeAllocationFailure model);
    List<TradeAllocationFailure> toModels(List<TradeAllocationFailureEntity> entities);
}
