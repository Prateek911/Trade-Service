package com.tradex.trade.service.infrastructure.persistence.jpa;

import com.tradex.trade.service.domain.entity.ProcessedEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProcessedEventRepository extends JpaRepository<ProcessedEventEntity, Long> {
}
