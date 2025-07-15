package com.errami.mics.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="Configurations")
public class Configuration {
    @Id
    private UUID id;

    private String userId;

    private UUID carId;

    @ElementCollection
    private List<UUID> selectedOptionIds;

    private BigDecimal totalPrice;

    private LocalDateTime createdAt;

    @Column (nullable = false)
    boolean ordered;
}
