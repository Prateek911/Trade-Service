package com.tradex.trade.service.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PhoneNumberDTO extends ClientDTO{

    private String phoneNumber;

    private String label;
}
