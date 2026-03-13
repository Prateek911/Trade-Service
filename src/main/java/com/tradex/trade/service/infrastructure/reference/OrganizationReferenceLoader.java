package com.tradex.trade.service.infrastructure.reference;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradex.trade.service.application.dto.OrganizationCreateDTO;
import com.tradex.trade.service.application.dto.OrganizationDTO;
import com.tradex.trade.service.application.organization.OrganizationService;
import com.tradex.trade.service.domain.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@Profile("local")
@Slf4j
@RequiredArgsConstructor
public class OrganizationReferenceLoader {

    private final ObjectMapper objectMapper;
    private final OrganizationService organizationService;
    private final OrganizationRepository organizationRepository;

    @Value("${reference.organizations.path:classpath:reference/organizations.json}")
    private Resource organizationsResource;

    public OrganizationReferenceLoadResult loadOrganizations() {
        List<OrganizationCreateDTO> referenceOrganizations = readOrganizations();

        List<OrganizationCreateDTO> toCreate = new ArrayList<>();
        List<String> skippedRegNumbers = new ArrayList<>();

        for (OrganizationCreateDTO organization : referenceOrganizations) {
            String regNumber = organization.getRegNumber();

            if (!StringUtils.hasText(regNumber)) {
                throw new IllegalStateException("Organization regNumber is required for idempotent loading.");
            }

            if (organizationRepository.existsByRegNumber(regNumber)) {
                skippedRegNumbers.add(regNumber);
                continue;
            }

            toCreate.add(organization);
        }

        List<OrganizationDTO> createdOrganizations = toCreate.isEmpty()
                ? List.of()
                : organizationService.createOrganizations(toCreate);

        List<String> createdRegNumbers = createdOrganizations.stream()
                .map(OrganizationDTO::getRegNumber)
                .toList();

        OrganizationReferenceLoadResult result = OrganizationReferenceLoadResult.builder()
                .totalRecords(referenceOrganizations.size())
                .createdRecords(createdRegNumbers.size())
                .skippedRecords(skippedRegNumbers.size())
                .createdRegNumbers(createdRegNumbers)
                .skippedRegNumbers(skippedRegNumbers)
                .build();

        log.info("Local organization reference load completed. total={}, created={}, skipped={}",
                result.getTotalRecords(),
                result.getCreatedRecords(),
                result.getSkippedRecords());

        return result;
    }

    private List<OrganizationCreateDTO> readOrganizations() {
        try (InputStream inputStream = organizationsResource.getInputStream()) {
            return objectMapper.readValue(inputStream, new TypeReference<>() {
            });
        } catch (IOException ex) {
            throw new IllegalStateException("Failed to read organization reference data from " + organizationsResource, ex);
        }
    }
}
