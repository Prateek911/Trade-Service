package com.tradex.trade.service.infrastructure.mapper;

import com.tradex.trade.service.domain.entity.PhoneNumberEntity;
import com.tradex.trade.service.domain.model.PhoneNumber;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = DateMapper.class)
public interface PhoneNumberMapper {

    PhoneNumber toModel(PhoneNumberEntity entity);
    PhoneNumberEntity toEntity(PhoneNumber model);
    List<PhoneNumber> toModels(List<PhoneNumberEntity> entities);

}
