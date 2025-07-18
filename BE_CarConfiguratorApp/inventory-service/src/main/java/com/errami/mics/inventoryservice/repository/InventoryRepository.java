package com.errami.mics.inventoryservice.repository;

import com.errami.mics.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository <Inventory, Long> {

    public Optional<Inventory> findBySkuCode(String skuCode);

}
