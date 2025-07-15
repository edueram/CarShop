package com.errami.mics.inventoryservice.dto;

import java.math.BigDecimal;

public record InventoryResponse(Long id, String skuCode, BigDecimal quantity) {
}
