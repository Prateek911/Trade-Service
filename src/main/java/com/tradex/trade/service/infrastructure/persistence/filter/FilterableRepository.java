package com.tradex.trade.service.infrastructure.persistence.filter;

import com.tradex.trade.service.infrastructure.dto.FilterDTO;
import com.tradex.trade.service.domain.common.exception.PersistanceFailureException;
import com.tradex.trade.service.domain.common.exception.RecordNotFoundException;
import com.tradex.trade.service.domain.common.supers.Persistable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@Slf4j
@AllArgsConstructor
@NoRepositoryBean
public abstract class FilterableRepository<E extends Persistable, F extends FilterDTO> implements JpaRepository<E, Long> {

    private final EntityManager em;
    private final Class<E> entityType;
    private final Integer maxPageSize;

    public E persist(@Valid @NotNull E entity) {
        return findById(save(entity).getId()).orElseThrow(
                ()->new PersistanceFailureException(entityType, entity.getClass().getSimpleName())
        );
    }

    public  E getById(@NotNull Long id) {
        return findById(id).orElseThrow(
                () ->new RecordNotFoundException(entityType, id)
        );
    }

    protected abstract List<Predicate> contraints(CriteriaBuilder cb, Root<E> root, F filter);

    protected abstract List<Order> orderList(CriteriaBuilder cb, Root<E> root, F filter);

    public Page<E> filter(F filter) {
        final var cb = em.getCriteriaBuilder();
        final var clazz = entityType();
        final var query = cb.createQuery(clazz);
        final var root = query.from(clazz);

        final var conditions = contraints(cb, root, filter)
                .toArray(Predicate[]::new);
        query.select(root).where(conditions);

        final var size = filter.getSize();
        final var pageSize = (size==null || size == 0) ? maxPageSize : Math.min(size,maxPageSize);

        var offset = 0;

        int page = (filter.getPage() == null || filter.getPage() < 1)
                ? 0
                : filter.getPage() - 1;

        offset = page * pageSize;


        final var order = filter.getOrder();
        final var sortBy = filter.getSortBy();
        final var isMultipleSort = filter.getIsMultipleSort();

        if (isMultipleSort !=null && isMultipleSort) {
            var orders = orderList(cb, root, filter);
            query.orderBy(orders);
        }else if (hasText(sortBy)) {
            var sorting = root.get(sortBy);
            query.orderBy(order.asc()?cb.asc(sorting):cb.desc(sorting));

        }

        final var results = em.createQuery(query)
                .setFirstResult(offset)
                .setMaxResults(pageSize)
                .getResultList();

        var countQuery = countQuery(cb, clazz, conditions, filter);
        final var total = em.createQuery(countQuery).getSingleResult();
        final var pageNumber = offset / pageSize;


        return new PageImpl<>(results, PageRequest.of(pageNumber,pageSize),total);
    }

    protected CriteriaQuery<Long> countQuery(final CriteriaBuilder cb, final Class<E> clazz, final Predicate[] conditions, final F filter) {
        var countQuery= cb.createQuery(Long.class);
        countQuery = countQuery.select(cb.count(countQuery.from(clazz))).where(conditions);
        return countQuery;
    }

    public Class<E> entityType() {
        return this.entityType;
    }

}
