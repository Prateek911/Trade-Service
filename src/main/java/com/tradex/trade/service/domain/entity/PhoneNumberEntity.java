package com.tradex.trade.service.domain.entity;

import com.tradex.trade.service.domain.common.supers.Persistable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Objects;

@EnableJpaAuditing
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "phone_numbers")
public class PhoneNumberEntity extends Persistable {

    @NotBlank
    @Size(max = 16)
    @Column(name = "phone_number", nullable = false, length = 16)
    private String phoneNumber;

    @Column(name = "label", length = 32)
    private String label;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(getPhoneNumber(), ((PhoneNumberEntity)o).getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhoneNumber());
    }
}
