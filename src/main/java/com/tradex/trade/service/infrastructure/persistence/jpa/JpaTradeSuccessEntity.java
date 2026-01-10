package com.tradex.trade.service.infrastructure.persistence.jpa;

import com.tradex.trade.service.domain.entity.TradeAllocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTradeSuccessEntity extends JpaRepository<TradeAllocationEntity, Long> {
}
