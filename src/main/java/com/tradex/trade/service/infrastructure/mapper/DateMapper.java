package com.tradex.trade.service.infrastructure.mapper;

import org.mapstruct.Mapper;

import java.sql.Timestamp;
import java.time.*;

@Mapper(componentModel = "spring")
public interface DateMapper {

    ZoneId UTC = ZoneOffset.UTC;

    default Timestamp instantToTimestamp(Instant value) {
        return value == null ? null : Timestamp.from(value);
    }

    default Instant timestampToInstant(Timestamp value) {
        return value == null ? null : value.toInstant();
    }

    default Instant localDateToInstant(LocalDate value) {
        return value == null
                ? null
                : value.atStartOfDay(UTC).toInstant();
    }

    default LocalDate instantToLocalDate(Instant value) {
        return value == null
                ? null
                : value.atZone(UTC).toLocalDate();
    }

    default Instant localDateTimeToInstant(LocalDateTime value) {
        return value == null
                ? null
                : value.toInstant(ZoneOffset.UTC);
    }

    default LocalDateTime instantToLocalDateTime(Instant value) {
        return value == null
                ? null
                : LocalDateTime.ofInstant(value, UTC);
    }
}
