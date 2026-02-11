package com.tradex.trade.service.infrastructure.mapper;

import com.tradex.trade.service.infrastructure.persistence.organization.EmailAddressEntity;
import com.tradex.trade.service.domain.organization.EmailAddress;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = DateMapper.class)
public interface EmailAddressMapper {

    EmailAddress toModel(EmailAddressEntity model);
    EmailAddressEntity toEntity(EmailAddress model);
    List<EmailAddress> toModels(List<EmailAddressEntity> entities);
}
