package com.tradex.trade.service.infrastructure.persistence.filter;

import com.tradex.trade.service.infrastructure.persistence.dto.FilterDTO;
import com.tradex.trade.service.shared.PersistanceFailureException;
import com.tradex.trade.service.domain.exception.RecordNotFoundException;
import com.tradex.trade.service.infrastructure.persistence.Persistable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;

@Slf4j
@AllArgsConstructor
@NoRepositoryBean
public abstract class FilterableRepository<E extends Persistable, F extends FilterDTO> {

    private final EntityManager em;
    private final Class<E> entityType;
    private final Integer maxPageSize;

    protected EntityManager getEntityManager() {
        return this.em;
    }

    public E persist(@Valid @NotNull E entity) {
        var saved = saveEntity(entity);
        return findById(saved.getId()).orElseThrow(
                () -> new PersistanceFailureException(entityType, entity.getClass().getSimpleName())
        );
    }

    protected E saveEntity(E entity) {
        if (entity.getId() == null) {
            em.persist(entity);
            return entity;
        }
        return em.merge(entity);
    }

    public E getById(@NotNull Long id) {
        return findById(id).orElseThrow(
                () -> new RecordNotFoundException(entityType, id)
        );
    }

    protected abstract List<Predicate> constraints(CriteriaBuilder cb, Root<E> root, F filter);

    protected abstract List<Order> orderList(CriteriaBuilder cb, Root<E> root, F filter);

    public Page<E> filter(F filter) {
        final var cb = em.getCriteriaBuilder();
        final var clazz = entityType();
        final var query = cb.createQuery(clazz);
        final var root = query.from(clazz);

        final var conditions = constraints(cb, root, filter)
                .toArray(Predicate[]::new);
        query.select(root).where(conditions);

        final var size = filter.getSize();
        final var pageSize = (size == null || size == 0) ? maxPageSize : Math.min(size, maxPageSize);

        var offset = 0;

        int page = (filter.getPage() == null || filter.getPage() < 1)
                ? 0
                : filter.getPage() - 1;

        offset = page * pageSize;


        final var order = filter.getOrder();
        final var sortBy = filter.getSortBy();
        final var isMultipleSort = filter.getIsMultipleSort();

        if (isMultipleSort != null && isMultipleSort) {
            var orders = orderList(cb, root, filter);
            query.orderBy(orders);
        } else if (hasText(sortBy)) {
            var sorting = root.get(sortBy);
            query.orderBy(order.asc() ? cb.asc(sorting) : cb.desc(sorting));

        }

        final var results = em.createQuery(query)
                .setFirstResult(offset)
                .setMaxResults(pageSize)
                .getResultList();

        var countQuery = countQuery(cb, clazz, conditions, filter);
        final var total = em.createQuery(countQuery).getSingleResult();
        final var pageNumber = offset / pageSize;


        return new PageImpl<>(results, PageRequest.of(pageNumber, pageSize), total);
    }

    protected CriteriaQuery<Long> countQuery(final CriteriaBuilder cb, final Class<E> clazz, final Predicate[] conditions, final F filter) {
        var countQuery = cb.createQuery(Long.class);
        countQuery = countQuery.select(cb.count(countQuery.from(clazz))).where(conditions);
        return countQuery;
    }

    public Class<E> entityType() {
        return this.entityType;
    }

    public <S extends E> S save(S entity) {
        if (entity.getId() == null) {
            em.persist(entity);
            return entity;
        }
        return em.merge(entity);
    }

    public <S extends E> Iterable<S> saveAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        for (S e : entities) {
            result.add(save(e));
        }
        return result;
    }

    public boolean existsById(Long aLong) {
        return findById(aLong).isPresent();
    }

    public long count() {
        var cb = em.getCriteriaBuilder();
        var cq = cb.createQuery(Long.class);
        cq.select(cb.count(cq.from(entityType)));
        return em.createQuery(cq).getSingleResult();
    }

    public void deleteById(Long aLong) {
        findById(aLong).ifPresent(e -> em.remove(em.contains(e) ? e : em.merge(e)));
    }

    public void delete(E entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }

    public void deleteAllById(Iterable<? extends Long> longs) {
        for (Long id : longs) deleteById(id);
    }

    public void deleteAll(Iterable<? extends E> entities) {
        for (E e : entities) delete(e);
    }

    public void deleteAll() {
        var cb = em.getCriteriaBuilder();
        var cq = cb.createCriteriaDelete(entityType);
        cq.from(entityType);
        em.createQuery(cq).executeUpdate();
    }

    public Optional<E> findById(Long id) {
        return Optional.ofNullable(em.find(entityType, id));
    }

    public Iterable<E> findAll() {
        var cb = em.getCriteriaBuilder();
        var cq = cb.createQuery(entityType);
        var root = cq.from(entityType);
        cq.select(root);
        return em.createQuery(cq).getResultList();
    }

    public Iterable<E> findAll(Sort sort) {
        if (sort == null || sort.isUnsorted()) return findAll();
        var cb = em.getCriteriaBuilder();
        var cq = cb.createQuery(entityType);
        var root = cq.from(entityType);
        List<Order> orders = new ArrayList<>();
        sort.forEach(order -> {
            if (order.isAscending()) orders.add(cb.asc(root.get(order.getProperty())));
            else orders.add(cb.desc(root.get(order.getProperty())));
        });
        cq.select(root).orderBy(orders);
        return em.createQuery(cq).getResultList();
    }

    public Page<E> findAll(Pageable pageable) {
        var cb = em.getCriteriaBuilder();
        var cq = cb.createQuery(entityType);
        var root = cq.from(entityType);

        if (pageable.getSort() != null && pageable.getSort().isSorted()) {
            List<Order> orders = new ArrayList<>();
            pageable.getSort().forEach(order -> {
                if (order.isAscending()) orders.add(cb.asc(root.get(order.getProperty())));
                else orders.add(cb.desc(root.get(order.getProperty())));
            });
            cq.orderBy(orders);
        }

        cq.select(root);
        var query = em.createQuery(cq)
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize());

        var results = query.getResultList();

        var countCq = cb.createQuery(Long.class);
        countCq.select(cb.count(countCq.from(entityType)));
        var total = em.createQuery(countCq).getSingleResult();

        return new PageImpl<>(results, pageable, total);
    }

    public Iterable<E> findAllById(Iterable<Long> ids) {
        List<Long> idList = new ArrayList<>();
        ids.forEach(idList::add);
        if (idList.isEmpty()) return List.of();
        var cb = em.getCriteriaBuilder();
        var cq = cb.createQuery(entityType);
        var root = cq.from(entityType);
        cq.select(root).where(root.get("id").in(idList));
        return em.createQuery(cq).getResultList();
    }

}
