package com.tradex.trade.service.domain.common;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Persistable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="created_at", nullable=false, updatable=false)
    private String createdAt;

    @Column(name="updated_at")
    private String updatedAt;

}
