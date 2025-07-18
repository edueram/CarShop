package com.errami.mics.carservice.controller;


import com.errami.mics.carservice.dto.CarRequest;
import com.errami.mics.carservice.dto.CarResponse;
import com.errami.mics.carservice.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor
public class CarController {
    public final CarService carService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarResponse createCar(@RequestBody CarRequest carRequest) {
        return carService.createCar(carRequest);
    }


@GetMapping
@ResponseStatus(HttpStatus.OK)
    public List<CarResponse> getAllCars() {
        return carService.getAllCars();
}

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarResponse getCarById(@PathVariable("id") UUID id) {

        return carService.getCarById(id);
    }

    @GetMapping("/stock/{id}")
    public ResponseEntity<CarResponse> getCar_existInStock(@PathVariable("id") UUID id) {
        Optional<CarResponse> optCarResponse= carService.getCarById_inStock(id);
        if(optCarResponse.isPresent()) {return ResponseEntity.ok(optCarResponse.get());}
        return ResponseEntity.notFound().build();
    }

    /*oder kompakter
    @GetMapping("/cars/{id}/in-stock")
    public ResponseEntity<CarResponse> getCarIfInStock(@PathVariable UUID id) {
    return carService.getCarById_inStock(id)
        .map(ResponseEntity::ok)                     // Wenn Auto vorhanden → 200 OK mit Inhalt
        .orElse(ResponseEntity.notFound().build());  // Wenn nicht → 404 Not Found
}
     */

    @GetMapping("/stock")
    public ResponseEntity<List<CarResponse>> getAllCars_exitsInStock() {
         List<CarResponse> carsInStock= carService.getAllCars_inStock();
         if(carsInStock.isEmpty()) {return ResponseEntity.noContent().build();}
         return ResponseEntity.ok(carsInStock);
    }

}
