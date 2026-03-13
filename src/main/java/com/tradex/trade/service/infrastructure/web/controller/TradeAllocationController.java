package com.tradex.trade.service.infrastructure.web.controller;

import com.tradex.trade.service.application.allocation.TradeAllocationService;
import com.tradex.trade.service.application.dto.TradeAllocationDTO;
import com.tradex.trade.service.application.dto.TradeAllocationFilterDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trade-allocations")
@RequiredArgsConstructor
@Tag(name = "Trade Allocations", description = "Dashboard for trade allocations")
public class TradeAllocationController {

    private final TradeAllocationService service;

    @Operation(summary = "Search trade allocations based on filters")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "allocations retrieved successfully"),
    })
    @GetMapping
    public ResponseEntity<Page<TradeAllocationDTO>> findAll(@Valid TradeAllocationFilterDTO filter) {
        return ResponseEntity.ok(service.search(filter));
    }


}
