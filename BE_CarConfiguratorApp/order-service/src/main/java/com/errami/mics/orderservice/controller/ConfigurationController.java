package com.errami.mics.orderservice.controller;

import com.errami.mics.orderservice.dto.ConfigurationRequest;
import com.errami.mics.orderservice.dto.ConfigurationResponse;
import com.errami.mics.orderservice.model.Configuration;
import com.errami.mics.orderservice.service.ConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/configuration")
public class ConfigurationController {

    private final ConfigurationService configurationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConfigurationResponse createConfiguration(@RequestBody ConfigurationRequest configuration) {
        Configuration config= configurationService.createConfiguration(configuration);
        return new ConfigurationResponse(
                config.getId(),
                config.getUserId(),
                config.getCarId(),
                config.getSelectedOptionIds(),
                config.getTotalPrice(),
                config.getCreatedAt(),
                config.isOrdered()
        );
    }


    @GetMapping("/{config_id}")
    @ResponseStatus(HttpStatus.OK)
    public ConfigurationResponse getConfiguration(@PathVariable("config_id") UUID configurationId) {
        return configurationService.getConfiguration(configurationId);
    }

    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ConfigurationResponse> getAllConfigurationsOfUser(@PathVariable("userId") String userId) {
        return configurationService.getAllConfigurationsOfUser(userId);

    }

    @PutMapping
    public ConfigurationResponse updateConfiguration(@RequestBody ConfigurationRequest configuration) {
       return configurationService.updateConfiguration(configuration);
    }


}
