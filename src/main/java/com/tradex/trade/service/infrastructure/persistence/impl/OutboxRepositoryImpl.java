package com.tradex.trade.service.infrastructure.persistence.impl;

import com.tradex.trade.service.domain.common.exception.RecordNotFoundException;
import com.tradex.trade.service.domain.entity.OutboxEntity;
import com.tradex.trade.service.domain.model.Outbox;
import com.tradex.trade.service.domain.repository.OutboxRepository;
import com.tradex.trade.service.infrastructure.mapper.OutboxMapper;
import com.tradex.trade.service.infrastructure.persistence.jpa.JpaOutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OutboxRepositoryImpl implements OutboxRepository {


    private final JpaOutboxRepository repository;
    private final OutboxMapper mapper;

    @Override
    public Outbox findById(Long id) {
        return mapper.toModel(repository.findById(id).orElseThrow(()->new RecordNotFoundException(OutboxEntity.class, id)));
    }

    @Override
    public Outbox save(Outbox model) {
        return mapper.toModel(repository.save(mapper.toEntity(model)));
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public List<Outbox> findNextBatchForUpdate(Pageable pageable) {
        return mapper.toModels(repository.findNextBatchForUpdate(pageable));
    }
}
