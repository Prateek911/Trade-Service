package com.tradex.trade.service.infrastructure.persistence.impl;

import com.tradex.trade.service.domain.entity.OutboxEntity;
import com.tradex.trade.service.domain.repository.OutboxRepository;
import com.tradex.trade.service.infrastructure.persistence.jpa.jpaOutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OutboxRepositoryImpl implements OutboxRepository {


    private final jpaOutboxRepository repository;

    @Override
    public Optional<OutboxEntity> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public OutboxEntity save(OutboxEntity entity) {
        return repository.save(entity);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public List<OutboxEntity> findNextBatchForUpdate(Pageable pageable) {
        return repository.findNextBatchForUpdate(pageable);
    }
}
