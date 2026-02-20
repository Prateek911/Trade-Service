package com.tradex.trade.service.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationCreateDTO extends ServerDTO{

    @NotBlank
    private String sourceId;

    @NotBlank
    private String sourceName;

    @NotBlank
    private String exchangeId;

    private List<KycCreateDTO> legalEntityKyc;

    private Set<PhoneNumberCreateDTO> phoneNumbers;

    private Set<EmailAddressCreateDTO> emailAddresses;

    @NotBlank
    private String regNumber;

    @NotBlank
    private String shortName;

    @NotBlank
    private String longName;

}
