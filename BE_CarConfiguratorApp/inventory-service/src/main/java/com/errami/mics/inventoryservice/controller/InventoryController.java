package com.errami.mics.inventoryservice.controller;

import com.errami.mics.inventoryservice.dto.InventoryResponse;
import com.errami.mics.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{skuCode}")
    public InventoryResponse checkStock(@PathVariable("skuCode") String skuCode){
    return inventoryService.checkStockBySkuCode(skuCode);
    }
}
