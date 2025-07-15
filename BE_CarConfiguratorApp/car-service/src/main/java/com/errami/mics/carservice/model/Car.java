package com.errami.mics.carservice.model;


import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Builder
@Table(name="car")
public class Car {

    @Id
    @GeneratedValue
    private UUID id;
    private String make;
    private String model;
    private String year;
    private BigDecimal basePrice;
    private String skuCode;

    /*
    private String color;
    private String engine;
    private String rims;
    private boolean extraOptions;
    private double totalPrice;
    */


 /*
    @ElementCollection
    @CollectionTable(name = "car_extra_options", joinColumns = @JoinColumn(name = "car_id"))
    private List<ExtraOption> extraOptions = new ArrayList<>();
 */



}

