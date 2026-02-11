package com.tradex.trade.service.infrastructure.persistence.organization;

import com.tradex.trade.service.domain.organization.Organization;
import com.tradex.trade.service.domain.repository.OrganizationRepository;
import com.tradex.trade.service.infrastructure.mapper.OrganizationMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OrganizationRepositoryImpl implements OrganizationRepository {

    private final JpaOrganizationRepository repository;
    private final OrganizationMapper mapper;

    @Override
    public Organization findById(Long id) {

        var entity = repository.getById(id);
        return mapper.toModel(entity);
    }

    public Organization save(OrganizationEntity entity) {
        return mapper.toModel(repository.save(entity));
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public List<Organization> findEligibleForAllocation(String sourceName) {
        return mapper.toModels(repository.findEligibleForAllocation(sourceName));
    }
}
