package com.tradex.trade.service.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class KycCreateDTO extends ServerDTO{

    @NotBlank
    String kycStatus;

    @NotBlank
    String countryCode;

}
