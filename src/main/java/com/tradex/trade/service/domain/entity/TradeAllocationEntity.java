package com.tradex.trade.service.domain.entity;

import com.tradex.trade.service.domain.common.supers.Persistable;
import com.tradex.trade.service.domain.common.enums.Status;
import com.tradex.trade.service.domain.model.AllocationLeg;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.Instant;
import java.util.List;

@EnableJpaAuditing
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "trade_allocations",
uniqueConstraints = {
        @UniqueConstraint(
                name="uk_trade_allocation_allocation_id",
                columnNames = "allocation_id"
        )
},
indexes = {
        @Index(
        name = "idx_trade_allocation_status",
        columnList = "status"
),
        @Index(
                name = "idx_trade_allocation_allocated_at",
                columnList = "allocated_at"
        )
})
public class TradeAllocationEntity extends Persistable {

    @Column(name = "trade_execution_id", nullable = false, updatable = false)
    private String tradeExecutionId;

    @Column(name = "allocation_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "allocation_result", columnDefinition = "jsonb")
    private List<AllocationLeg> allocationResult;

    @Column(name = "rule_version", nullable = false)
    private String ruleVersion;

    @Column(name = "allocated_at", nullable = false, updatable = false)
    private Instant allocatedAt = Instant.now();
}
