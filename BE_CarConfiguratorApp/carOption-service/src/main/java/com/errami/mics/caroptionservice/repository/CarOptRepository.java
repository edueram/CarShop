package com.errami.mics.caroptionservice.repository;

import com.errami.mics.caroptionservice.model.CarOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface CarOptRepository extends JpaRepository<CarOption, UUID > {
}

