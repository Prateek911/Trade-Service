package com.tradex.trade.service.infrastructure.mapper;

import org.mapstruct.Mapper;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Mapper(componentModel = "spring")
public interface DateMapper {

    default Timestamp map(Instant value) {
        return value == null ? null : Timestamp.from(value);
    }

    default Instant map(Timestamp value) {
        return value == null ? null : value.toInstant();
    }

    default Timestamp map(LocalDate value) {
        return value == null ? null : Timestamp.valueOf(value.atStartOfDay());
    }

    default LocalDate mapToLocalDate(Timestamp value) {
        return value == null ? null : value.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
