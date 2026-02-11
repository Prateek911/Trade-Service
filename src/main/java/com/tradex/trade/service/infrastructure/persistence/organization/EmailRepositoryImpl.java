package com.tradex.trade.service.infrastructure.persistence.organization;

import com.tradex.trade.service.domain.organization.EmailAddress;
import com.tradex.trade.service.domain.repository.EmailAddressRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailRepositoryImpl implements EmailAddressRepository {
    @Override
    public EmailAddress findById(Long aLong) {
        return null;
    }


    public EmailAddress save(EmailAddress aggregate) {
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }
}
