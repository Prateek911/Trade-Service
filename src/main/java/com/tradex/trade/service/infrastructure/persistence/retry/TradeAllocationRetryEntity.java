package com.tradex.trade.service.infrastructure.persistence.retry;

import com.tradex.trade.service.infrastructure.persistence.Persistable;
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
    private int retryCount=0;

    @Column(name = "max_retries", nullable = false)
    private int maxRetries;

    @Column(name = "next_attempt_at", nullable = false)
    private Instant nextAttemptAt;

    @Column(name = "last_failure_code", nullable = false)
    private String lastFailureCode;

    public static TradeAllocationRetryEntity from(String tradeExecutionId, int maxRetries, Instant nextAttemptAt, String lastFailureCode) {
        return TradeAllocationRetryEntity.builder()
                .tradeExecutionId(tradeExecutionId)
                .retryCount(0)
                .maxRetries(maxRetries)
                .nextAttemptAt(nextAttemptAt)
                .lastFailureCode(lastFailureCode)
                .build();
    }

}
