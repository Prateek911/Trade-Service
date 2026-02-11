package com.tradex.trade.service.infrastructure.mapper;

import com.tradex.trade.service.infrastructure.persistence.outbox.OutboxEntity;
import com.tradex.trade.service.domain.outbox.Outbox;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = DateMapper.class)
public interface OutboxMapper {

    Outbox toModel(OutboxEntity entity);
    OutboxEntity toEntity(Outbox entity);
    List<Outbox> toModels(List<OutboxEntity> entityList);

}
