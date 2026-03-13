package com.tradex.trade.service.infrastructure.reference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class OrganizationReferenceLoadResult {

    private final int totalRecords;
    private final int createdRecords;
    private final int skippedRecords;
    private final List<String> createdRegNumbers;
    private final List<String> skippedRegNumbers;

}
