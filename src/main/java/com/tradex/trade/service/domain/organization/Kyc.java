package com.tradex.trade.service.domain.organization;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class Kyc {
    String kycStatus;
    String countryCode;
}
