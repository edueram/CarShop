package com.errami.mics.inventoryservice.repository;

import com.errami.mics.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository <Inventory, Long> {

    Inventory findBySkuCode(String skuCode);

}
