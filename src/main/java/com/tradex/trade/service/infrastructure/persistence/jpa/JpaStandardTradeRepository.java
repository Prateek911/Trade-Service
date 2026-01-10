package com.tradex.trade.service.infrastructure.persistence.jpa;

import com.tradex.trade.service.domain.entity.StandardTradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaStandardTradeRepository extends JpaRepository<StandardTradeEntity, Long> {

    Optional<StandardTradeEntity> findByTradeExecutionId(String tradeExecutionId);
}
