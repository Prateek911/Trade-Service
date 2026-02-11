package com.tradex.trade.service.domain.allocation;

import com.tradex.trade.service.domain.common.repository.IRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface TradeRetryRepository extends IRepository<TradeAllocationRetry,Long> {

    List<TradeAllocationRetry> findRetryableBatch(Instant now, Pageable pageable);
    void deleteByTradeExecutionId(String tradeExecutionId);
    void upsertRetry(String tradeExecutionId, Instant nextRetryAt, String lastError);
}
