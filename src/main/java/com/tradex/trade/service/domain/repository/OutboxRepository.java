package com.tradex.trade.service.domain.repository;

import com.tradex.trade.service.domain.common.repository.IRepository;
import com.tradex.trade.service.infrastructure.persistence.outbox.OutboxEntity;
import com.tradex.trade.service.domain.outbox.Outbox;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OutboxRepository extends IRepository<Outbox, Long> {
    List<OutboxEntity> findNextBatchForUpdate(Pageable pageable);
    Outbox save(OutboxEntity entity);
}
