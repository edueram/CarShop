package com.errami.mics.orderservice.repository;

import com.errami.mics.orderservice.model.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ConfigurationRepository extends JpaRepository<Configuration, UUID> {
    List<Configuration> findAllByUserId(String userId);
}
