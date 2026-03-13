package com.tradex.trade.service.infrastructure.mapper;

import com.tradex.trade.service.application.dto.TradeAllocationDTO;
import com.tradex.trade.service.domain.allocation.TradeAllocation;
import com.tradex.trade.service.infrastructure.persistence.allocation.TradeAllocationEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {EntityReferenceDtoMapper.class,DateMapper.class})
public interface TradeAllocationDTOMapper {

    TradeAllocationDTO toDTO(TradeAllocationEntity entity);
    TradeAllocationDTO toDTO(TradeAllocation model);
    TradeAllocation toModel(TradeAllocationDTO dto);
    List<TradeAllocationDTO> toDTOs(List<TradeAllocationEntity> DTOs);
    List<TradeAllocation> toModels(List<TradeAllocationDTO> dtos);
}
