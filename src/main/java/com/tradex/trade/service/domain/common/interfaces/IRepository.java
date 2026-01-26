package com.tradex.trade.service.domain.common.interfaces;

import java.util.Optional;

public interface IRepository<T extends AggregateRoot<ID>,ID> {

    T findById(ID id);
    boolean existsById(ID id);
}
