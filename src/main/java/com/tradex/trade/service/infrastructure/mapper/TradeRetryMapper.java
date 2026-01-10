package com.tradex.trade.service.infrastructure.mapper;

import com.tradex.trade.service.domain.entity.TradeAllocationRetryEntity;
import com.tradex.trade.service.domain.model.TradeAllocationRetry;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = DateMapper.class)
public interface TradeRetryMapper {

    TradeAllocationRetryEntity toEntity(TradeAllocationRetryEntity model);
    TradeAllocationRetry toModel(TradeAllocationRetryEntity entity);
    List<TradeAllocationRetry> toModels(List<TradeAllocationRetryEntity> entities);
}
