package com.tradex.trade.service.infrastructure.mapper;

import com.tradex.trade.service.infrastructure.persistence.processed.ProcessedEventEntity;
import com.tradex.trade.service.domain.event.ProcessedEvent;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = DateMapper.class)
public interface ProcessedEventMapper {

    ProcessedEvent toModel(ProcessedEventEntity entity);
    ProcessedEventEntity toEntity(ProcessedEvent model);
    List<ProcessedEvent> toModels(List<ProcessedEventEntity> entities);
}
