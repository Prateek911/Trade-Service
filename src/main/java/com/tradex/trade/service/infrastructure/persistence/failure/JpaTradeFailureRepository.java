package com.tradex.trade.service.infrastructure.persistence.failure;

import com.tradex.trade.service.infrastructure.persistence.allocation.TradeAllocationFailureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTradeFailureRepository extends JpaRepository<TradeAllocationFailureEntity, Long> {
}
