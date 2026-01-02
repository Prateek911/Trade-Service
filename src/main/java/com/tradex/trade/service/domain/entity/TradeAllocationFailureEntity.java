package com.tradex.trade.service.domain.entity;

import com.tradex.trade.service.domain.common.enums.FailureCategory;
import com.tradex.trade.service.domain.common.supers.Persistable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.Instant;

@EnableJpaAuditing
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "trade_allocation_failures",
uniqueConstraints = {
        @UniqueConstraint(
                name="uk_trade_allocation_failure_trade_execution_id",
                columnNames = "trade_execution_id"
        )
})
public class TradeAllocationFailureEntity extends Persistable {

    @Column(name = "trade_execution_id", nullable = false, updatable = false)
    private String tradeExecutionId;

    @Column(name = "failure_category", nullable = false)
    @Enumerated(EnumType.STRING)
    private FailureCategory failureCategory;

    @Column(name = "failure_code", nullable = false)
    private String failureCode;

    @Column(name = "failure_message", nullable = false, length = 1000)
    private String failureMessage;

    @Column(name = "retryable", nullable = false)
    private boolean retryable;

    @Column(name = "failed_at", nullable = false, updatable = false)
    private Instant failedAt = Instant.now();
}
