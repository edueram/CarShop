package com.errami.mics.caroptionservice.controller;

import com.errami.mics.caroptionservice.dto.CarOptRequest;
import com.errami.mics.caroptionservice.dto.CarOptResponse;
import com.errami.mics.caroptionservice.dto.ErrorResponse;
import com.errami.mics.caroptionservice.service.CarOptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/caroption")
@RequiredArgsConstructor
public class CarOptController {

    private final CarOptService carOptService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarOptResponse createCar(@RequestBody CarOptRequest carOptRequest){
        return carOptService.createCarOpt(carOptRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CarOptResponse> getAllCarOpt(){
        return carOptService.getAllCarOpts();}

    // In Stock

    @GetMapping("/stock/{id}")
    public ResponseEntity<?> getCar_existInStock(@PathVariable("id") UUID id) {
        Optional<CarOptResponse> optCarResponse= carOptService.getCarOptById_inStock(id);
        if(optCarResponse.isPresent()) {
            return ResponseEntity.ok(optCarResponse.get());}

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("Car part not found", 404));
    }

    @GetMapping("/stock")
    public ResponseEntity<List<CarOptResponse>> getAllCars_exitsInStock() {
        List<CarOptResponse> carsInStock= carOptService.getAllCarOpts_inStock();
        if(carsInStock.isEmpty()) {return ResponseEntity.noContent().build();}
        return ResponseEntity.ok(carsInStock);
    }


}
