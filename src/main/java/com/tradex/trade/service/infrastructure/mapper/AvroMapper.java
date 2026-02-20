package com.tradex.trade.service.infrastructure.mapper;

import org.mapstruct.Mapper;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface AvroMapper {

    default CharSequence map(Enum<?> status) {
        return status.name();
    }

    default CharSequence currentTimestamp() {
        return java.time.Instant.now().toString();
    }

    default Double map(BigDecimal value) {
        return value != null ? value.doubleValue() : null;
    }

}
