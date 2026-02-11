package com.tradex.trade.service.infrastructure.persistence.retry;

import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    void deleteByTradeExecutionId(String tradeExecutionId);

    @Modifying
    @Transactional
    @Query(
            value = """
        INSERT INTO allocation_retry (
            trade_execution_id,
            retry_count,
            next_retry_at,
            last_error
        )
        VALUES (
            :tradeExecutionId,
            1,
            :nextRetryAt,
            :lastError
        )
        ON CONFLICT (trade_execution_id)
        DO UPDATE SET
            retry_count   = allocation_retry.retry_count + 1,
            next_retry_at = EXCLUDED.next_retry_at,
            last_error    = EXCLUDED.last_error
        """,
            nativeQuery = true
    )
    void upsertRetry(
            @Param("tradeExecutionId") String tradeExecutionId,
            @Param("nextRetryAt") Instant nextRetryAt,
            @Param("lastError") String lastError
    );

}
