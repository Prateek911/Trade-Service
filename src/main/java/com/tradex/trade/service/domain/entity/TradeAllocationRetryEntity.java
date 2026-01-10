package com.tradex.trade.service.domain.entity;

import com.tradex.trade.service.domain.common.supers.Persistable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "retry",
        uniqueConstraints = {
        @UniqueConstraint(name = "uk_trade_execution_id", columnNames = "trade_execution_id")
},
        indexes = {
        @Index(name = "idx_next_attempt_at", columnList = "next_attempt_at")
})
public class TradeAllocationRetryEntity extends Persistable {

    @Column(name = "trade_execution_id", nullable = false, updatable = false)
    private String tradeExecutionId;

    @Column(name = "retry_count", nullable = false)
    private int retryCount;

    @Column(name = "max_retries", nullable = false)
    private int maxRetries;

    @Column(name = "next_attempt_at", nullable = false)
    private Instant nextAttemptAt;

    @Column(name = "last_failure_code", nullable = false)
    private String lastFailureCode;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

}
