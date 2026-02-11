package com.tradex.trade.service.domain.organization;

import com.tradex.trade.service.domain.common.AggregateRoot;
import com.tradex.trade.service.domain.entity.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@RequiredArgsConstructor
@ToString
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Organization extends Entity implements AggregateRoot<Long> {

    private String sourceId;
    private String sourceName;
    private String exchangeId;
    private String status;
    private List<Kyc> legalEntityKyc;
    private Set<PhoneNumber> phoneNumbers;
    private Set<EmailAddress> emailAddresses;
    private LocalDateTime archivedAt;
    private Boolean archived;
    private String regNumber;
    private String shortName;
    private String longName;

    private record Kyc(String kycStatus, String countryCode){};
}
