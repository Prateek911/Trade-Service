package com.tradex.trade.service.infrastructure.persistence.trade;

import com.tradex.trade.service.domain.common.enums.Status;
import com.tradex.trade.service.infrastructure.persistence.Persistable;
import com.tradex.trade.service.domain.trade.TradeSide;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.math.BigDecimal;
import java.time.Instant;

@EnableJpaAuditing
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="standard_trades",
uniqueConstraints = {
        @UniqueConstraint(
        name = "uk_standard_trade_execution_id",
        columnNames = "trade_execution_id"
)},
indexes = {
        @Index(name = "idx_standard_trade_trade_id", columnList = "trade_id"),
        @Index(name = "idx_standard_trade_instrument", columnList = "instrument_id"),
        @Index(name = "idx_standard_trade_created_at", columnList = "created_at")
})
@EqualsAndHashCode(callSuper = true)
public class StandardTradeEntity extends Persistable {

    @Column(name = "trade_execution_id", nullable = false, updatable = false)
    private String tradeExecutionId;

    @Column(name = "instrument_id", nullable = false, updatable = false)
    private String instrumentId;

    @Column(name = "side", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private TradeSide side;

    @Column(name = "quantity", nullable = false, updatable = false)
    private BigDecimal quantity;

    @Column(name = "price", nullable = false, updatable = false)
    private BigDecimal price;

    @Column(name = "currency", nullable = false, updatable = false)
    private String currency;

    @Column(name = "source_system", nullable = false, updatable = false)
    private String sourceSystem;

    @Column(name = "trade_timestamp", nullable = false)
    private Instant tradeTimestamp;

    @Column(name = "allocation_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status=Status.RECEIVED;

    @Column(name = "ingested_at", nullable = false)
    private Instant ingestedAt = Instant.now();

    @Column(name="allocation_attempted_at")
    private Instant allocationAttemptedAt;

    @Column(name="allocation_attempts")
    private Integer allocationAttempts = 0;

}
