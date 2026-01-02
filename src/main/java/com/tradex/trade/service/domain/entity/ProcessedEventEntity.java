package com.tradex.trade.service.domain.entity;

import com.tradex.trade.service.domain.common.supers.Persistable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.Instant;

@EnableJpaAuditing
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "processed_event",
indexes = {
        @Index(name = "idx_processedAt_id", columnList = "processed_at", unique = true)
})
public class ProcessedEventEntity extends Persistable {

    @Column(name = "event_id", nullable = false)
    private String eventId;

    @Column(name = "processed_at", nullable = false)
    private Instant processedAt = Instant.now();

}
