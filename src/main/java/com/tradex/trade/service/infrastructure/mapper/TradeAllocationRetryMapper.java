package com.tradex.trade.service.infrastructure.mapper;

import com.tradex.trade.service.domain.entity.TradeAllocationRetryEntity;
import com.tradex.trade.service.domain.model.TradeAllocationRetry;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = DateMapper.class)
public interface TradeAllocationRetryMapper {

TradeAllocationRetry toModel(TradeAllocationRetryEntity entity);
TradeAllocationRetryEntity toEntity(TradeAllocationRetry model);
List<TradeAllocationRetry> toModels(List<TradeAllocationRetryEntity> entityList);

}
