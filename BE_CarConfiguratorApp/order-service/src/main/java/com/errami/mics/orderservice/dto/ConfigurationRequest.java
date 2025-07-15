package com.errami.mics.orderservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ConfigurationRequest(UUID id, String userId, UUID carId, List<UUID> selectedOptionIds, BigDecimal totalPrice, LocalDateTime createdAt, boolean ordered) {


}
