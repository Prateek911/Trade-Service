package com.tradex.trade.service.infrastructure.web.controller;

import com.tradex.trade.service.application.dto.StandardTradeDTO;
import com.tradex.trade.service.application.dto.StandardTradeFilterDTO;
import com.tradex.trade.service.application.trade.StandardTradeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/standard-trades")
@RequiredArgsConstructor
@Tag(name = "Standard Trades", description = "Search and inspect standard trades")
public class StandardTradeController {

    private final StandardTradeService service;

    @Operation(summary = "Search standard trades based on filters")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "standard trades retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<Page<StandardTradeDTO>> search(
            @Valid StandardTradeFilterDTO filter
    ) {
        return ResponseEntity.ok(service.search(filter));
    }
}
