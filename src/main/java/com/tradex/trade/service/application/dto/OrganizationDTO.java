package com.tradex.trade.service.application.dto;

import com.tradex.trade.service.domain.common.enums.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDTO extends DTO {

    private String sourceId;

    private String sourceName;

    private String exchangeId;

    private List<KycDTO> legalEntityKyc;

    private Set<PhoneNumberDTO> phoneNumbers;

    private Set<EmailAddressDTO> emailAddresses;

    private String regNumber;

    private String shortName;

    private String longName;

    private Status status;

    private LocalDateTime archivedAt;

    private Boolean archived;

}
