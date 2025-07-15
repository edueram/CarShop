package com.errami.mics.caroptionservice.service;

import com.errami.mics.caroptionservice.client.InventoryClient;
import com.errami.mics.caroptionservice.dto.CarOptRequest;
import com.errami.mics.caroptionservice.dto.CarOptResponse;
import com.errami.mics.caroptionservice.dto.InventoryOptionResponse;
import com.errami.mics.caroptionservice.model.CarOption;
import com.errami.mics.caroptionservice.repository.CarOptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RequiredArgsConstructor
@Slf4j
@Service
public class CarOptService {
    private final CarOptRepository carOptRepository;

    public CarOptResponse createCarOpt(CarOptRequest carOptRequest) {
        CarOption carOption = CarOption.builder()
                .id(carOptRequest.id())
                .option_type(carOptRequest.option_type())
                .option_value(carOptRequest.option_value())
                .additional_price(carOptRequest.additional_price())
                .skuCode(carOptRequest.skuCode())
                .build();

        carOptRepository.save(carOption);
        log.info("Car option created: {}", carOption);
        return new CarOptResponse(carOption.getId(), carOption.getOption_type(), carOption.getOption_value(), carOption.getAdditional_price(), carOption.getSkuCode());

    }

    public List<CarOptResponse> getAllCarOpts() {
        List<CarOption> carOptions = carOptRepository.findAll();

        return carOptions.stream().map(
                 carOption ->  new CarOptResponse(carOption.getId(), carOption.getOption_type(), carOption.getOption_value(), carOption.getAdditional_price(), carOption.getSkuCode())).toList();
    }


    public CarOptResponse getCarById(UUID id) {

        CarOption carOption= carOptRepository.findById(id).get();
        return new CarOptResponse(carOption.getId(), carOption.getOption_type(), carOption.getOption_value(), carOption.getAdditional_price(), carOption.getSkuCode());

    }


    //// AB hier mit Lager checken
   private final InventoryClient inventoryClient;
    public InventoryOptionResponse  checkQuantiyInStock(String skuCode){
   // Anders als bei car-service (dort habe ich Resttemplate )
        InventoryOptionResponse inventoryOptionResponse = inventoryClient.checkQuantiyInStock(skuCode);

        if (inventoryOptionResponse == null) {
            log.info ("Part with number {} is not available.", skuCode);
            return new InventoryOptionResponse(skuCode, BigDecimal.ZERO); // Beispiel
        }

        return inventoryOptionResponse;
    }

    public List<CarOptResponse> getAllCarOpts_inStock() {
        return carOptRepository.findAll().stream()
                .filter(carOpt -> checkQuantiyInStock(carOpt.getSkuCode()).quantity().compareTo(BigDecimal.ZERO) > 0)
                .map(carOpt -> new CarOptResponse(
                        carOpt.getId(),
                        carOpt.getOption_type(),
                        carOpt.getOption_value(),
                        carOpt.getAdditional_price(),
                        carOpt.getSkuCode()
                    ))
                .toList();
    }

    public Optional<CarOptResponse> getCarOptById_inStock(UUID id) {

        Optional <CarOption> optionalCarOpt= carOptRepository.findById(id);
        if (optionalCarOpt.isEmpty()) return Optional.empty();

        CarOption carOpt = optionalCarOpt.get();
        if (checkQuantiyInStock(carOpt.getSkuCode()).quantity().compareTo(BigDecimal.ZERO) > 0){
            log.info("Car mit skuCode {} found, but not in stock", carOpt.getSkuCode());
            return Optional.empty();
        }
        return Optional.of(new CarOptResponse(carOpt.getId(), carOpt.getOption_type(),carOpt.getOption_value(), carOpt.getAdditional_price(), carOpt.getSkuCode()));
    }



}
