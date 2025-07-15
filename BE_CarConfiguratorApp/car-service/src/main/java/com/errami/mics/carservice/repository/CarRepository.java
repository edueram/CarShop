package com.errami.mics.carservice.repository;

import com.errami.mics.carservice.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
public interface CarRepository extends JpaRepository<Car, UUID> {
}
