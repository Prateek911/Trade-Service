package com.tradex.trade.service.infrastructure.web.controller;

import com.tradex.trade.service.application.dto.OrganizationCreateDTO;
import com.tradex.trade.service.application.dto.OrganizationDTO;
import com.tradex.trade.service.application.organization.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
@Tag(name = "Organizations", description = "Organization management")
public class OrganizationController {

    private final OrganizationService organizationService;

    @Operation(summary = "Create organization")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Organization created"),
            @ApiResponse(responseCode = "400", description = "Validation failed")
    })
    @PostMapping
    public ResponseEntity<OrganizationDTO> createOrganization(@Valid @RequestBody OrganizationCreateDTO createDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.createOrganization(createDTO));
    }

    @Operation(summary = "Create organizations in bulk")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Organizations created"),
            @ApiResponse(responseCode = "400", description = "Validation failed")
    })
    @PostMapping("/bulk")
    public ResponseEntity<List<OrganizationDTO>> createOrganizations(
            @Valid @RequestBody List<@Valid OrganizationCreateDTO> createDTOs
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.createOrganizations(createDTOs));
    }
}
