package com.tradex.trade.service.infrastructure.mapper;

import com.tradex.trade.service.application.dto.EntityDTO;
import com.tradex.trade.service.domain.entity.Entity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses={DateMapper.class})
public interface EntityReferenceDtoMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Entity toModel(EntityDTO entityDto);

    List<Entity> toModels(List<EntityDTO> entityDtos);

    List<EntityDTO> toDtos(List<Entity> entities);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    EntityDTO toDto(Entity entity);
}
