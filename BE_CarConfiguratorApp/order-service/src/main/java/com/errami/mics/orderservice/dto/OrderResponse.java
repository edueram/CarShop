package com.errami.mics.orderservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record OrderResponse(UUID id, String userId, String userName, String userEmail,UUID configurationId, LocalDateTime orderDate, BigDecimal finalPrice, String status) {
}
