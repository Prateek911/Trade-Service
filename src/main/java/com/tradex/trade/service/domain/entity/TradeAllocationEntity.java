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
import java.util.ArrayList;
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
                name = "idx_trade_allocation_created_at",
                columnList = "created_at"
        )
})
public class TradeAllocationEntity extends Persistable {

    @Column(name = "trade_execution_id", nullable = false, updatable = false)
    private String tradeExecutionId;

    @Column(name = "allocation_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(
            mappedBy = "allocation",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<AllocationLegEntity> legs = new ArrayList<>();

    public void replaceLegs(List<AllocationLegEntity> newLegs) {
        legs.clear();
        legs.addAll(newLegs);
    }

    @Column(name = "rule_code", nullable = false)
    private String ruleCode;
}
