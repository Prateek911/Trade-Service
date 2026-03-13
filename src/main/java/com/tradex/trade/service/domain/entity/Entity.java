package com.tradex.trade.service.domain.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Data
@SuperBuilder
@NoArgsConstructor
public class Entity {

    private Long id;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
