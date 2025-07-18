package com.errami.mics.orderservice.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class OrderCreatedEvent {
    private String orderId;
    private String userId;
    private String userName;
    private String userEmail;
    private String orderStatus;
    private UUID configurationId;
    private LocalDateTime orderDate;
    private BigDecimal finalPrice;

}
