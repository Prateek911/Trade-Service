package com.tradex.trade.service.infrastructure.mapper;

import com.tradex.trade.service.domain.entity.TradeAllocationFailureEntity;
import com.tradex.trade.service.domain.model.TradeAllocationFailure;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = DateMapper.class)
public interface TradeFailureMapper {

    TradeAllocationFailureEntity toModel(TradeAllocationFailure model);
    TradeAllocationFailure toEntity(TradeAllocationFailureEntity entity);
    List<TradeAllocationFailure> toModels(List<TradeAllocationFailureEntity> entities);
}
