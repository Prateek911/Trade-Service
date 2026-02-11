package com.tradex.trade.service.infrastructure.persistence.organization;

import com.tradex.trade.service.infrastructure.persistence.filter.FilterableRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaOrganizationRepository implements FilterableRepository<OrganizationEntity, OrganizationFilterDTO> {

    @Query("select o from OrganizationEntity o where o.status = 'ACTIVE' " +
            "and o.sourceName = :sourceName")
    List<OrganizationEntity> findEligibleForAllocation(String sourceName);
}
