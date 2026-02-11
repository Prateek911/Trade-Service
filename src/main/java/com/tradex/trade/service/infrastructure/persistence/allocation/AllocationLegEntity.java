package com.tradex.trade.service.infrastructure.persistence.allocation;

import com.tradex.trade.service.infrastructure.persistence.Persistable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(
        name = "trade_allocation_leg",
        indexes = {
                @Index(name = "idx_allocation_leg_org", columnList = "organization_id"),
                @Index(name = "idx_allocation_leg_trade", columnList = "trade_execution_id")
        }
)
public class AllocationLegEntity extends Persistable {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "allocation_id",
            nullable = false,
            updatable = false
    )
    private TradeAllocationEntity allocation;

    @Column(name = "trade_execution_id", nullable = false, updatable = false)
    private String tradeExecutionId;

    @Column(name = "organization_id", nullable = false, updatable = false)
    private String organizationId;

    @Column(name = "allocated_quantity", nullable = false, updatable = false)
    private BigDecimal allocatedQuantity;

    @Column(name = "allocated_notional", nullable = false, updatable = false)
    private BigDecimal allocatedNotional;

    @Column(name = "currency", nullable = false, updatable = false)
    private String currency;
}
