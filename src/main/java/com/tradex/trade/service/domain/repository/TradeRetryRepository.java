package com.tradex.trade.service.domain.repository;

import com.tradex.trade.service.domain.common.interfaces.IRepository;
import com.tradex.trade.service.domain.model.TradeAllocationRetry;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface TradeRetryRepository extends IRepository<TradeAllocationRetry,Long> {

    List<TradeAllocationRetry> findRetryableBatch(Instant now, Pageable pageable);
}
