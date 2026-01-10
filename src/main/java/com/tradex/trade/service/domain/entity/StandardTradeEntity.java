package com.tradex.trade.service.domain.entity;

import com.tradex.trade.service.domain.common.supers.Persistable;
import com.tradex.trade.service.domain.common.enums.TradeSide;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;

@EnableJpaAuditing
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "trade_id", nullable = false, updatable = false)
    private String tradeId;

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

    @Column(name = "execution_timestamp", nullable = false, updatable = false)
    private Instant executionTimestamp;

    @Column(name = "source_system", nullable = false, updatable = false)
    private String sourceSystem;

    @NotNull
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "raw_event_payload", columnDefinition = "jsonb", nullable = false)
    private Map<String, Object> eventPayload;
}
