package com.tradex.trade.service.infrastructure.mapper;

import com.tradex.trade.service.application.dto.OrganizationCreateDTO;
import com.tradex.trade.service.application.dto.OrganizationDTO;
import com.tradex.trade.service.domain.organization.Organization;
import com.tradex.trade.service.infrastructure.persistence.organization.OrganizationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = {EntityReferenceDtoMapper.class,DateMapper.class})
public interface OrganizationDTOMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    Organization toModel(OrganizationCreateDTO dto);

    Organization toModel(OrganizationDTO dto);

    OrganizationDTO toDTO(Organization model);

    List<Organization> toModels(List<OrganizationDTO> dtos);

}
