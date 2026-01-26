package com.tradex.trade.service.domain.repository;

import com.tradex.trade.service.domain.common.interfaces.IRepository;
import com.tradex.trade.service.domain.entity.OutboxEntity;
import com.tradex.trade.service.domain.model.Outbox;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OutboxRepository extends IRepository<Outbox, Long> {
    List<OutboxEntity> findNextBatchForUpdate(Pageable pageable);
    Outbox save(OutboxEntity entity);
}
