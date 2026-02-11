package com.tradex.trade.service.infrastructure.persistence.outbox;

import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaOutboxRepository extends JpaRepository<OutboxEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
    SELECT o FROM OutboxEntity o
    WHERE o.status = 'NEW'
    ORDER BY o.createdAt
    """)
    List<OutboxEntity> findNextBatchForUpdate(Pageable pageable);
}
