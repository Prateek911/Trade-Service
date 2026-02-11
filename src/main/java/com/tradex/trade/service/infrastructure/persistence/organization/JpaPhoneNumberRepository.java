package com.tradex.trade.service.infrastructure.persistence.organization;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPhoneNumberRepository extends CrudRepository<PhoneNumberEntity, Long> {
}
