package com.tradex.trade.service.infrastructure.mapper;

import org.mapstruct.Mapper;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;

@Mapper(componentModel = "spring")
public interface DateMapper {

    default Timestamp map(LocalDate value) {
        return value == null ? null : Timestamp.valueOf(value.atStartOfDay());
    }

    default LocalDate map(Timestamp value) {
        return value == null ? null : value.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
