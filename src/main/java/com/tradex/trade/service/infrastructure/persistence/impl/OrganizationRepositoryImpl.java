package com.tradex.trade.service.infrastructure.persistence.impl;

import com.tradex.trade.service.domain.entity.OrganizationEntity;
import com.tradex.trade.service.domain.model.Organization;
import com.tradex.trade.service.domain.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrganizationRepositoryImpl implements OrganizationRepository {
    @Override
    public Organization findById(Long aLong) {
        return null;
    }


    public Organization save(OrganizationEntity aggregate) {
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }
}
