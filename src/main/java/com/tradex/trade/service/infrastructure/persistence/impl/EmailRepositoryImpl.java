package com.tradex.trade.service.infrastructure.persistence.impl;

import com.tradex.trade.service.domain.model.EmailAddress;
import com.tradex.trade.service.domain.repository.EmailAddressRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailRepositoryImpl implements EmailAddressRepository {
    @Override
    public EmailAddress findById(Long aLong) {
        return null;
    }

    @Override
    public EmailAddress save(EmailAddress aggregate) {
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }
}
