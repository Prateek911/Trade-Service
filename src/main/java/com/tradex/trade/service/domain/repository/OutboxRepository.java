package com.tradex.trade.service.domain.repository;

import com.tradex.trade.service.domain.common.interfaces.IRepository;
import com.tradex.trade.service.domain.entity.OutboxEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OutboxRepository extends IRepository<OutboxEntity, Long> {
    List<OutboxEntity> findNextBatchForUpdate(Pageable pageable);
}
