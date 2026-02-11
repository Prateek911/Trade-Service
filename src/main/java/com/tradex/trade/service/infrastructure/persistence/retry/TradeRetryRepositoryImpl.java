package com.tradex.trade.service.infrastructure.persistence.retry;

import com.tradex.trade.service.domain.allocation.TradeAllocationRetry;
import com.tradex.trade.service.domain.allocation.TradeRetryRepository;
import com.tradex.trade.service.infrastructure.mapper.TradeRetryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
public class TradeRetryRepositoryImpl implements TradeRetryRepository {

    private final JpaTradeRetryRepository repository;
    private final TradeRetryMapper mapper;

    @Override
    public TradeAllocationRetry findById(Long aLong) {
        return null;
    }

    public TradeAllocationRetry save(TradeAllocationRetry aggregate) {
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<TradeAllocationRetry> findRetryableBatch(Instant now, Pageable pageable) {
        return mapper.toModels(repository.findRetryableBatch(now,pageable));
    }

    @Override
    public void deleteByTradeExecutionId(String tradeExecutionId) {
        repository.deleteByTradeExecutionId(tradeExecutionId);
    }

    @Override
    public void upsertRetry(String tradeExecutionId, Instant nextRetryAt, String lastError) {
        repository.upsertRetry(tradeExecutionId,nextRetryAt,lastError);
    }
}
