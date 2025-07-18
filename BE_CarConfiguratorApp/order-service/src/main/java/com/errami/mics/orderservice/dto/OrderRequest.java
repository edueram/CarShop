package com.errami.mics.orderservice.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderRequest(UUID id, String userId, String userName, String userEmail, UUID configurationId, BigDecimal finalPrice) {
}
