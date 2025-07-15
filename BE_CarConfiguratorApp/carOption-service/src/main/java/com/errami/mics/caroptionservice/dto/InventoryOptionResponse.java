package com.errami.mics.caroptionservice.dto;

import java.math.BigDecimal;

public record InventoryOptionResponse(String skuCode, BigDecimal quantity) {
}
