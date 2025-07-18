package com.errami.mics.caroptionservice.client;


import com.errami.mics.caroptionservice.dto.InventoryOptionResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/api/inventory")
public interface InventoryClient {

    @GetExchange("/{skuCode}")
    InventoryOptionResponse checkQuantiyInStock(@PathVariable("skuCode") String skuCode);

}
