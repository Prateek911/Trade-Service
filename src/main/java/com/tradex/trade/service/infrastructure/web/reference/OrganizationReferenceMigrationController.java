package com.tradex.trade.service.infrastructure.web.reference;

import com.tradex.trade.service.infrastructure.reference.OrganizationReferenceLoadResult;
import com.tradex.trade.service.infrastructure.reference.OrganizationReferenceLoader;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("local")
@RequestMapping("/api/migration/reference/organizations")
@RequiredArgsConstructor
@Tag(name = "Reference Migration", description = "Local-only reference data migration APIs")
public class OrganizationReferenceMigrationController {

    private final OrganizationReferenceLoader organizationReferenceLoader;

    @Operation(summary = "Load organization reference data from local JSON")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Organizations loaded successfully")
    })
    @PostMapping("/load")
    public ResponseEntity<OrganizationReferenceLoadResult> loadOrganizations() {
        return ResponseEntity.ok(organizationReferenceLoader.loadOrganizations());
    }
}
