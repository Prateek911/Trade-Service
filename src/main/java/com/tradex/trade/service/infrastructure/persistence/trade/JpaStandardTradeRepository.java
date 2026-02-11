package com.tradex.trade.service.infrastructure.persistence.trade;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaStandardTradeRepository extends JpaRepository<StandardTradeEntity, Long> {

    Optional<StandardTradeEntity> findByTradeExecutionId(String tradeExecutionId);

    Boolean existsByTradeExecutionId(String tradeExecutionId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = """
        select st FROM StandardTradeEntity st
        WHERE st.status = Status.PENDING
        ORDER BY st.ingestedAt
        LIMIT :batchSize
        """)
    List<StandardTradeEntity> lockPendingTrades(
            @Param("batchSize") int batchSize
    );
}
