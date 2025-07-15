package com.errami.mics.carservice.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CarRequest(UUID id, String make, String model, String year, BigDecimal basePrice, String skuCode) {
}
