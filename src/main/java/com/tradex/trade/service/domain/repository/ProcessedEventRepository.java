package com.tradex.trade.service.domain.repository;

import com.tradex.trade.service.domain.common.interfaces.IRepository;
import com.tradex.trade.service.domain.entity.ProcessedEventEntity;
import com.tradex.trade.service.domain.model.ProcessedEvent;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedEventRepository extends IRepository<ProcessedEvent,Long> {
}
