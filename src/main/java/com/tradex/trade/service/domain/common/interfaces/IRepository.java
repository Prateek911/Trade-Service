package com.tradex.trade.service.domain.common.interfaces;

import java.util.Optional;

public interface IRepository<T extends AggregateRoot<ID>,ID> {

    Optional<T> findById(ID id);
    T save(T aggregate);
    boolean existsById(ID id);
}
