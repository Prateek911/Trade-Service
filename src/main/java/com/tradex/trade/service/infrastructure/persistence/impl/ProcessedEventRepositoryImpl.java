package com.tradex.trade.service.infrastructure.persistence.impl;

import com.tradex.trade.service.domain.model.ProcessedEvent;
import com.tradex.trade.service.domain.repository.ProcessedEventRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProcessedEventRepositoryImpl implements ProcessedEventRepository {
    @Override
    public ProcessedEvent findById(Long aLong) {
        return null;
    }

    @Override
    public ProcessedEvent save(ProcessedEvent aggregate) {
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }
}
