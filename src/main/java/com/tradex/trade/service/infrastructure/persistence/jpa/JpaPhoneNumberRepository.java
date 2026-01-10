package com.tradex.trade.service.infrastructure.persistence.jpa;

import com.tradex.trade.service.domain.entity.PhoneNumberEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPhoneNumberRepository extends CrudRepository<PhoneNumberEntity, Long> {
}
