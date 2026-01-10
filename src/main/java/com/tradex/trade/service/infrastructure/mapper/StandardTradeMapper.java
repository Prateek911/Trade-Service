package com.tradex.trade.service.infrastructure.mapper;

import com.tradex.trade.service.domain.entity.StandardTradeEntity;
import com.tradex.trade.service.domain.model.StandardTrade;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = DateMapper.class)
public interface StandardTradeMapper {

    StandardTrade toModel(StandardTradeEntity entity);
    StandardTradeEntity toEntity(StandardTrade model);
    List<StandardTrade> toModels(List<StandardTradeEntity> entityList);
}
