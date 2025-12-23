package com.tradex.trade.service.domain.entity;

import com.tradex.trade.service.domain.common.Persistable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.util.*;

import static java.util.Objects.isNull;

@EnableJpaAuditing
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "organizations")
public class OrganizationEntity extends Persistable {

    @NotBlank
    @Size(min = 1, max = 32)
    @Column(name = "source_id", nullable = false, updatable = false, length = 32)
    private String sourceId;

    @NotBlank
    @Size(min = 1, max = 32)
    @Column(name = "exchange_id", nullable = false, updatable = false, length = 32)
    private String exchangeId;

    @Column(name = "status", nullable = false, length = 32)
    private String status;

    @ElementCollection
    @CollectionTable(name = "legal_entity_kyc",
            joinColumns = @JoinColumn(name = "organization_id"))
    private List<Kyc> legalEntityKyc;

    @OneToMany(cascade =  CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Set<PhoneNumberEntity> phoneNumbers;

    @OneToMany(cascade =  CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Set<EmailAddressEntity> emailAddresses;

    @Column(name = "archived_at")
    private LocalDateTime archivedAt;

    @Column(name = "archived")
    private Boolean archived;

    @Column(name = "reg_number",nullable = false, length = 64)
    private String regNumber;

    @Column(name = "short_name", nullable = false, length = 128)
    private String shortName;

    @Column(name = "long_name", nullable = false, length = 512)
    private String longName;

    public void add(EmailAddressEntity... emails) {
        getEmailAddresses().addAll(Arrays.asList(emails));
    }

    public void add(PhoneNumberEntity... phoneNumbers) {
        getPhoneNumbers().addAll(Arrays.asList(phoneNumbers));
    }

    public Set<PhoneNumberEntity> getPhoneNumbers() {
        if(isNull(phoneNumbers)) {
            phoneNumbers = new HashSet<>();
        }
        return phoneNumbers;
    }

    public Set<EmailAddressEntity> getEmailAddresses() {
        if(isNull(emailAddresses)) {
            emailAddresses = new HashSet<>();
        }
        return emailAddresses;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (o==null || getClass() != o.getClass()) return false;
        return Objects.equals(getExchangeId(), ((OrganizationEntity)o).getExchangeId());
    }

    @Override
    public int hashCode() {return Objects.hash(getExchangeId());}

}
