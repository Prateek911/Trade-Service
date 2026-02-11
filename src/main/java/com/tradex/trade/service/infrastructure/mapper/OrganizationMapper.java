package com.tradex.trade.service.infrastructure.mapper;

import com.tradex.trade.service.infrastructure.persistence.organization.OrganizationEntity;
import com.tradex.trade.service.domain.organization.Organization;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = DateMapper.class)
public interface OrganizationMapper {

    Organization toModel(OrganizationEntity entity);
    OrganizationEntity toEntity(Organization model);
    List<Organization> toModels(List<OrganizationEntity> entities);
}
