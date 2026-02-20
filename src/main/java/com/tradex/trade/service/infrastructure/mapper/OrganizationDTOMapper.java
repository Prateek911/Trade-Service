package com.tradex.trade.service.infrastructure.mapper;

import com.tradex.trade.service.application.dto.OrganizationCreateDTO;
import com.tradex.trade.service.application.dto.OrganizationDTO;
import com.tradex.trade.service.domain.organization.Organization;
import com.tradex.trade.service.infrastructure.persistence.organization.OrganizationEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = DateMapper.class)
public interface OrganizationDTOMapper {

    Organization toModel(OrganizationCreateDTO dto);
    Organization toModel(OrganizationDTO dto);
    OrganizationDTO toDTO(Organization model);
    List<Organization> toModels(List<OrganizationDTO> dtos);

}
