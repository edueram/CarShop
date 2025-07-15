package com.errami.mics.orderservice.repository;

import com.errami.mics.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findAllByUserId(String userId);

    Order findByConfigurationId(UUID configurationId);
}

