package com.tradex.trade.service.domain.repository;

import com.tradex.trade.service.domain.common.repository.IRepository;
import com.tradex.trade.service.domain.organization.Organization;

import java.util.List;

public interface OrganizationRepository extends IRepository<Organization,Long> {

    List<Organization> findEligibleForAllocation(String sourceName);

}
