package com.errami.mics.inventoryservice.dto;

import java.math.BigDecimal;

public record InventoryRequest(Long id, String skuCode, BigDecimal quantity) {
}
