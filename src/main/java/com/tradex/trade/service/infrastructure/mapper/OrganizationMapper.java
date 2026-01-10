package com.tradex.trade.service.infrastructure.mapper;

import com.tradex.trade.service.domain.entity.OrganizationEntity;
import com.tradex.trade.service.domain.model.Organization;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = DateMapper.class)
public interface OrganizationMapper {

    Organization toModel(OrganizationEntity entity);
    OrganizationEntity toEntity(Organization model);
    List<Organization> toModels(List<OrganizationEntity> entities);
}
