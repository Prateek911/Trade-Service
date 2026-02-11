package com.tradex.trade.service.domain.common.repository;

import com.tradex.trade.service.domain.common.AggregateRoot;

public interface IRepository<T extends AggregateRoot<ID>,ID> {

    T findById(ID id);
    boolean existsById(ID id);
}
