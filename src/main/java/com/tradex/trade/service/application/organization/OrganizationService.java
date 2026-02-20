package com.tradex.trade.service.application.organization;

import com.tradex.trade.service.application.dto.OrganizationCreateDTO;
import com.tradex.trade.service.application.dto.OrganizationDTO;
import com.tradex.trade.service.domain.organization.Organization;
import com.tradex.trade.service.domain.repository.OrganizationRepository;
import com.tradex.trade.service.infrastructure.mapper.OrganizationDTOMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository repository;
    private final OrganizationDTOMapper mapper;

    @Transactional
    public OrganizationDTO createOrganization(OrganizationCreateDTO createDTO) {

        Organization model = mapper.toModel(createDTO);

        Organization result = repository.save(model);

        return mapper.toDTO(result);

    }

}
