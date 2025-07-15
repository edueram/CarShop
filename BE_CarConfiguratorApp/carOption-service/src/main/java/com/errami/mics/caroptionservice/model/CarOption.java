package com.errami.mics.caroptionservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Builder
@Table(name="car_option")
public class CarOption {
    @Id
    @GeneratedValue
    private UUID id;
    private String option_type;
    private String option_value;
    private BigDecimal additional_price;
    private String skuCode;

}
