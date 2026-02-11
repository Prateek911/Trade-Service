package com.tradex.trade.service.infrastructure.persistence.processed;

import com.tradex.trade.service.domain.event.ProcessedEvent;
import com.tradex.trade.service.domain.repository.ProcessedEventRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProcessedEventRepositoryImpl implements ProcessedEventRepository {
    @Override
    public ProcessedEvent findById(Long aLong) {
        return null;
    }

    public ProcessedEvent save(ProcessedEventEntity entity) {
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }
}
