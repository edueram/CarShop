package com.errami.mics.caroptionservice.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CarOptResponse(UUID id, String option_type, String option_value, BigDecimal additional_price, String skuCode) {
}
