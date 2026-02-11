package com.tradex.trade.service.infrastructure.persistence.organization;

import com.tradex.trade.service.domain.organization.PhoneNumber;
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
