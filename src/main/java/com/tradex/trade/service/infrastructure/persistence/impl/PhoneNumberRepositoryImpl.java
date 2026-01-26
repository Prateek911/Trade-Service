package com.tradex.trade.service.infrastructure.persistence.impl;

import com.tradex.trade.service.domain.entity.PhoneNumberEntity;
import com.tradex.trade.service.domain.model.PhoneNumber;
import com.tradex.trade.service.domain.repository.PhoneNumberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PhoneNumberRepositoryImpl implements PhoneNumberRepository {
    @Override
    public PhoneNumber findById(Long aLong) {
        return null;
    }

    public PhoneNumber save(PhoneNumberEntity entity) {
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }
}
