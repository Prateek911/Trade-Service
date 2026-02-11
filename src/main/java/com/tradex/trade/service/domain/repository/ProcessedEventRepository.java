package com.tradex.trade.service.domain.repository;

import com.tradex.trade.service.domain.common.repository.IRepository;
import com.tradex.trade.service.domain.event.ProcessedEvent;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedEventRepository extends IRepository<ProcessedEvent,Long> {
}
