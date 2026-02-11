package com.tradex.trade.service.infrastructure.persistence.outbox;

import com.tradex.trade.service.domain.exception.RecordNotFoundException;
import com.tradex.trade.service.domain.outbox.Outbox;
import com.tradex.trade.service.domain.repository.OutboxRepository;
import com.tradex.trade.service.infrastructure.mapper.OutboxMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public List<OutboxEntity> findNextBatchForUpdate(Pageable pageable) {
        return repository.findNextBatchForUpdate(pageable);
    }

    @Override
    public Outbox save(OutboxEntity entity) {
        return mapper.toModel(repository.save(entity));
    }
}
