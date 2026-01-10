package com.tradex.trade.service.infrastructure.persistence.jpa;

import com.tradex.trade.service.domain.entity.TradeAllocationRetryEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface JpaTradeRetryRepository extends CrudRepository<TradeAllocationRetryEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
        SELECT r
        FROM TradeAllocationRetryEntity r
        WHERE r.nextAttemptAt <= :now
          AND r.retryCount < r.maxRetries
        ORDER BY r.nextAttemptAt
        """)
    List<TradeAllocationRetryEntity> findRetryableBatch(
            @Param("now") Instant now,
            Pageable pageable
    );

}
