package com.tradex.trade.service.infrastructure.persistence.allocation;

import com.tradex.trade.service.domain.view.AllocationView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaTradeAllocationRepository extends JpaRepository<TradeAllocationEntity, Long> {

    @Query("""
        SELECT
            a.tradeExecutionId as tradeExecutionId,
            a.status as status,
            a.ruleCode as ruleCode,
            l.organizationId as organizationId,
            l.allocatedQuantity as allocatedQuantity,
            l.allocatedNotional as allocatedNotional,
            l.currency as currency
        FROM TradeAllocationEntity a
        JOIN a.legs l
        WHERE a.tradeExecutionId = :tradeExecutionId
        """)
    List<AllocationView> findAllocationView(
            @Param("tradeExecutionId") String tradeExecutionId
    );

    Optional<TradeAllocationEntity> findByTradeExecutionId(String tradeExecutionId);
}
