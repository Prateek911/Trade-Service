package com.tradex.trade.service.infrastructure.persistence.organization;

import com.tradex.trade.service.infrastructure.persistence.dto.OrganizationFilterDTO;
import com.tradex.trade.service.infrastructure.persistence.filter.FilterableRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JpaOrganizationRepository extends FilterableRepository<OrganizationEntity, OrganizationFilterDTO> {

    public JpaOrganizationRepository(EntityManager em, @Value("${service.query.size}") Integer pageSize) {
        super(em, OrganizationEntity.class, pageSize);
    }

    @Override
    protected List<Predicate> constraints(CriteriaBuilder cb, Root<OrganizationEntity> root, OrganizationFilterDTO filter) {
        return new ArrayList<>();
    }

    @Override
    protected List<Order> orderList(CriteriaBuilder cb, jakarta.persistence.criteria.Root<OrganizationEntity> root, OrganizationFilterDTO filter) {
        return new ArrayList<>();
    }

    public List<OrganizationEntity> findEligibleForAllocation(String sourceName) {
        var em = getEntityManager();
        return em.createQuery("select o from OrganizationEntity o where o.status = 'ACTIVE' and o.sourceName = :sourceName", OrganizationEntity.class)
                .setParameter("sourceName", sourceName)
                .getResultList();
    }
}
