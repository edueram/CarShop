package com.errami.mics.inventoryservice.service;

import com.errami.mics.inventoryservice.dto.InventoryRequest;
import com.errami.mics.inventoryservice.dto.InventoryResponse;
import com.errami.mics.inventoryservice.model.Inventory;
import com.errami.mics.inventoryservice.repository.InventoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Builder
@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryResponse createInventory(InventoryRequest inventoryRequest) {
        Inventory inventory = Inventory.builder()
                .id(inventoryRequest.id())
                .skuCode(inventoryRequest.skuCode())
                .quantity(inventoryRequest.quantity())
                .build();

        inventoryRepository.save(inventory);
        log.info("Inventory created");
        return new InventoryResponse(inventory.getId(), inventory.getSkuCode(), inventory.getQuantity());
    }

    public InventoryResponse checkStockBySkuCode(String skuCode) {
        Inventory inventory = inventoryRepository.findBySkuCode(skuCode)
                .orElseThrow(() -> new EntityNotFoundException("Inventory with skuCode " + skuCode + " not found"));
        return new InventoryResponse(inventory.getId(), inventory.getSkuCode(), inventory.getQuantity());
    }





    public List<InventoryResponse> getAllInventories() {
        List<Inventory> inventoryList = inventoryRepository.findAll();
        List<InventoryResponse> inventoryResponseList = new ArrayList<>();
        for (Inventory inventory : inventoryList) {
            inventoryResponseList.add(new InventoryResponse(inventory.getId(), inventory.getSkuCode(), inventory.getQuantity()));
        }
        return inventoryResponseList;
    }

    public InventoryResponse updateInventoryById(InventoryRequest inventoryRequest) {
        Inventory inventory = inventoryRepository.findById(inventoryRequest.id()).orElse(null);
        assert inventory != null;
        inventory.setSkuCode(inventoryRequest.skuCode());
        inventory.setQuantity(inventoryRequest.quantity());
        inventoryRepository.save(inventory);
        log.info("Inventory updated");
        return new InventoryResponse(inventory.getId(), inventory.getSkuCode(), inventory.getQuantity());
    }

    public InventoryResponse updateInventoryBySkuCode(InventoryRequest inventoryRequest) {
        Inventory inventory = inventoryRepository.findBySkuCode(inventoryRequest.skuCode())
                .orElseThrow(() -> new EntityNotFoundException("Inventory with skuCode " + inventoryRequest.skuCode() + " not found"));

        inventory.setSkuCode(inventoryRequest.skuCode());
        inventory.setQuantity(inventoryRequest.quantity());

        Inventory updated = inventoryRepository.save(inventory);
        log.info("Inventory updated");

        return new InventoryResponse(updated.getId(), updated.getSkuCode(), updated.getQuantity());
    }


}
