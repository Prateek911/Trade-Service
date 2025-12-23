package com.tradex.trade.service.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Kyc {

    @Column(name="kyc_status")
    private String kycStatus;

    @NotBlank
    @Column(name="country_code")
    private String countryCode;
}
