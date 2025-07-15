package com.errami.mics.orderservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@Builder
@RequiredArgsConstructor
@Data
@Entity
@Table(name = "orders")
@AllArgsConstructor
public class Order {

    @Id
    private UUID id;
    private String userId;
    private UUID configurationId;
    private LocalDateTime orderDate;
    private BigDecimal finalPrice;
    private String status; //  pending, confirmed, cancelled

}
