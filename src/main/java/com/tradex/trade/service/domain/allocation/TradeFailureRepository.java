package com.tradex.trade.service.domain.allocation;

import com.tradex.trade.service.domain.common.repository.IRepository;
import com.tradex.trade.service.infrastructure.persistence.allocation.TradeAllocationFailureEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeFailureRepository extends IRepository<TradeAllocationFailure,Long> {
    TradeAllocationFailure save(TradeAllocationFailureEntity entity);
}
