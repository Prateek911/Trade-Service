package com.tradex.trade.service.infrastructure.persistence.jpa;

import com.tradex.trade.service.domain.entity.TradeAllocationFailureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTradeFailureRepository extends JpaRepository<TradeAllocationFailureEntity, Long> {
}
