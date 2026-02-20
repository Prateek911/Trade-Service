package com.tradex.trade.service.infrastructure.persistence.organization;

import com.tradex.trade.service.domain.organization.EmailAddress;
import com.tradex.trade.service.domain.repository.EmailAddressRepository;
import com.tradex.trade.service.infrastructure.mapper.EmailAddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class EmailRepositoryImpl implements EmailAddressRepository {

    private final JpaEmailRepository repository;
    private final EmailAddressMapper mapper;


    @Override
    public EmailAddress findById(Long id) {

            var entity = repository.findById(id).orElseThrow(() -> new RuntimeException("EmailAddress not found with id: " + id));
            return mapper.toModel(entity);

    }


    public EmailAddress save(EmailAddressEntity entity) {
        return mapper.toModel(repository.save(entity));
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
