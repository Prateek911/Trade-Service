package com.tradex.trade.service.infrastructure.web;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Trade Service API",
                version = "v1",
                description = "Public API for the Trade Service platform."
        )
)
public class OpenApiConfig {
}
