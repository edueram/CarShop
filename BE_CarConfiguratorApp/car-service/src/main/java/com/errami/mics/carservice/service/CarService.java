package com.errami.mics.carservice.service;

import com.errami.mics.carservice.dto.CarRequest;
import com.errami.mics.carservice.dto.CarResponse;
import com.errami.mics.carservice.dto.InventoryCarResponse;
import com.errami.mics.carservice.model.Car;
import com.errami.mics.carservice.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarService {

    // Section mit RestTemplate unten

    private final CarRepository carRepository;


    public CarResponse createCar(CarRequest carRequest) {
        Car car = Car.builder()
                .id(carRequest.id())
                .make(carRequest.make())
                .model(carRequest.model())
                .year(carRequest.year())
                .basePrice(carRequest.basePrice())
                .skuCode(carRequest.skuCode())
                .build();
        carRepository.save(car);
        log.info("Car created: {}", car);
        return new CarResponse(car.getId(), car.getMake(), car.getModel(),
                car.getYear(), car.getBasePrice(), car.getSkuCode());
    }


    public List<CarResponse> getAllCars() {
        List<Car> cars = carRepository.findAll();

        return cars.stream()
                .map(car -> new CarResponse(car.getId(), car.getMake(), car.getModel(),
                        car.getYear(), car.getBasePrice(), car.getSkuCode())).toList();
    }

    public CarResponse getCarById(UUID id) {

        Car car= carRepository.findById(id).get();
        return new CarResponse(car.getId(), car.getMake(), car.getModel(), car.getYear(), car.getBasePrice(), car.getSkuCode());

    }

    // Eigefügt für RestTemplate (Anfrage an Inventory-service )

    private final RestTemplate restTemplate;

    @Value("${inventory.service.url}")
    private String inventoryServerUrl;


    public InventoryCarResponse checkQuantiyInStock(String skuCode){
        String url = inventoryServerUrl + "/api/inventory/" + skuCode;
       try {
           InventoryCarResponse inventoryCarResponse = restTemplate.getForObject(url, InventoryCarResponse.class);
           // Optional: Fallback bei null
           if (inventoryCarResponse == null) {
               log.warn("Inventory response for skuCode {} was null", skuCode);
               return new InventoryCarResponse(skuCode, BigDecimal.ZERO); // Beispiel
           }

           return inventoryCarResponse;
       } catch (HttpClientErrorException.NotFound e) {
           log.info("Inventory response for skuCode {} was not found", skuCode);
           return new InventoryCarResponse(skuCode, BigDecimal.ZERO);
       }
       catch (Exception ex) {
           // Andere Fehler (Timeout, 500 etc.) loggen und ebenfalls 0 zurückgeben
           log.error("Error fetching inventory for skuCode {}: {}", skuCode, ex.getMessage());
           return new InventoryCarResponse(skuCode, BigDecimal.ZERO);
       }


    }



    public List<CarResponse> getAllCars_inStock() {
        return carRepository.findAll().stream()
                .filter(car -> checkQuantiyInStock(car.getSkuCode()).quantity().compareTo(BigDecimal.ZERO) > 0)
                .map(car -> new CarResponse(
                        car.getId(),
                        car.getMake(),
                        car.getModel(),
                        car.getYear(),
                        car.getBasePrice(),
                        car.getSkuCode()
                ))
                .toList();
    }



    public Optional<CarResponse> getCarById_inStock(UUID id) {

        Optional <Car> optionalCar= carRepository.findById(id);
        System.out.println("find car by id in car database: "+  optionalCar.isPresent());
        if (optionalCar.isEmpty()) return Optional.empty();

        Car car = optionalCar.get();
        if (checkQuantiyInStock(car.getSkuCode()).quantity().compareTo(BigDecimal.ZERO) <= 0){
            System.out.println("Quantity in stock: "+checkQuantiyInStock(car.getSkuCode()).quantity());
            log.info("Car mit skuCode {} found, but not in stock", car.getSkuCode());
            return Optional.empty();
        }
        return Optional.of(new CarResponse(car.getId(), car.getMake(), car.getModel(),
                car.getYear(), car.getBasePrice(), car.getSkuCode()));
    }

    /*Alternative und kompakt:
        public Optional<CarResponse> getCarById_inStock(UUID id) {
    return carRepository.findById(id)
        .filter(car -> checkQuantiyInStock(car.getSkuCode()).compareTo(BigDecimal.ZERO) > 0)
        .map(car -> new CarResponse(
            car.getId(),
            car.getMake(),
            car.getModel(),
            car.getYear(),
            car.getBasePrice(),
            car.getSkuCode()
        ));
}
     */


    //


}
