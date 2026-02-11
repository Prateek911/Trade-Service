package com.tradex.trade.service.infrastructure.mapper;

import com.tradex.trade.service.infrastructure.persistence.retry.TradeAllocationRetryEntity;
import com.tradex.trade.service.domain.allocation.TradeAllocationRetry;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = DateMapper.class)
public interface TradeAllocationRetryMapper {

TradeAllocationRetry toModel(TradeAllocationRetryEntity entity);
TradeAllocationRetryEntity toEntity(TradeAllocationRetry model);
List<TradeAllocationRetry> toModels(List<TradeAllocationRetryEntity> entityList);

}
