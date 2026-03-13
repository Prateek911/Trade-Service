package com.tradex.trade.service.infrastructure.mapper;

import com.tradex.trade.service.application.dto.StandardTradeDTO;
import com.tradex.trade.service.domain.trade.StandardTrade;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {EntityReferenceDtoMapper.class,DateMapper.class})
public interface StandardTradeDTOMapper {

    StandardTradeDTO toDTO(StandardTrade model);
    List<StandardTradeDTO> toDTOs(List<StandardTrade> models);
}
