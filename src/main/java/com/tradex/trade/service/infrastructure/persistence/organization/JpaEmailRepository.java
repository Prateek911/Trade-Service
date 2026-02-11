package com.tradex.trade.service.infrastructure.persistence.organization;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaEmailRepository extends CrudRepository<EmailAddressEntity, Long> {
}
