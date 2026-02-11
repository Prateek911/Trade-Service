package com.tradex.trade.service.infrastructure.mapper;

import com.tradex.trade.service.infrastructure.persistence.trade.StandardTradeEntity;
import com.tradex.trade.service.domain.trade.StandardTrade;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = DateMapper.class)
public interface StandardTradeMapper {

    StandardTrade toModel(StandardTradeEntity entity);
    StandardTradeEntity toEntity(StandardTrade model);
    List<StandardTrade> toModels(List<StandardTradeEntity> entityList);
}
