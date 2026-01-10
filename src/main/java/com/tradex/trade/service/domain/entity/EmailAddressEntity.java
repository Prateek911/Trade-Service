package com.tradex.trade.service.domain.entity;

import com.tradex.trade.service.domain.common.interfaces.AggregateRoot;
import com.tradex.trade.service.domain.common.supers.Persistable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "email_addresses")
public class EmailAddressEntity extends Persistable {

    @NotBlank
    @Email
    @Column(name = "email", nullable = false)
    private String email;

    private String type;

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (o==null || getClass() != o.getClass()) return false;
        return Objects.equals(getEmail(), ((EmailAddressEntity)o).getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail());
    }
}
