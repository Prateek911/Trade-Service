package com.tradex.trade.service.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ClientDTO extends DTO {

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
