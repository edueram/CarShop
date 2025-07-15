package com.errami.mics.orderservice.service;


import com.errami.mics.orderservice.dto.ConfigurationRequest;
import com.errami.mics.orderservice.dto.ConfigurationResponse;
import com.errami.mics.orderservice.model.Configuration;
import com.errami.mics.orderservice.repository.ConfigurationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.http.client.ClientHttpRequestFactoryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConfigurationService {

    private final ConfigurationRepository  configurationRepository;
    private final ClientHttpRequestFactoryBuilder clientHttpRequestFactoryBuilder;

    public Configuration createConfiguration(ConfigurationRequest configurationRequest) {
        Configuration configuration = Configuration.builder()
                .id(configurationRequest.id())
                .userId(configurationRequest.userId())
                .carId(configurationRequest.carId())
                .selectedOptionIds(configurationRequest.selectedOptionIds())
                .totalPrice(configurationRequest.totalPrice())
                .createdAt(configurationRequest.createdAt())
                .ordered(configurationRequest.ordered())
                .build();
        configurationRepository.save(configuration);

        log.info("Configuration created {}", configuration);
        return configuration;

    }

    public ConfigurationResponse getConfiguration(UUID configId) {
        Configuration configuration = configurationRepository.findById(configId).get();
        return new ConfigurationResponse(configuration.getId(),configuration.getUserId(),configuration.getCarId(),configuration.getSelectedOptionIds(),configuration.getTotalPrice(),configuration.getCreatedAt(),configuration.isOrdered());

    }

    public List<ConfigurationResponse> getAllConfigurationsOfUser(String userId) {
        List<Configuration> configurations = configurationRepository.findAllByUserId(userId);
        List<ConfigurationResponse> configurationResponses = new ArrayList<>();
        return configurations.stream().map(
                configuration ->  new ConfigurationResponse(configuration.getId(),configuration.getUserId(),configuration.getCarId(),
                        configuration.getSelectedOptionIds(),configuration.getTotalPrice(),configuration.getCreatedAt(), configuration.isOrdered())).toList();
    }

    public ConfigurationResponse updateConfiguration(ConfigurationRequest configurationRequest) {
        Configuration configuration = configurationRepository.findById(configurationRequest.id()) .orElseThrow(() -> new RuntimeException("Configuration not found"));
        configuration.setUserId(configurationRequest.userId());
        configuration.setCarId(configurationRequest.carId());
        configuration.setSelectedOptionIds(configurationRequest.selectedOptionIds());
        configuration.setTotalPrice(configurationRequest.totalPrice());
        configuration.setCreatedAt(configurationRequest.createdAt());
        configuration.setOrdered(configurationRequest.ordered());
        configurationRepository.save(configuration);
        log.info("Configuration updated {}", configuration);

        return new ConfigurationResponse(configuration.getId(),configuration.getUserId(),configuration.getCarId(),configuration.getSelectedOptionIds(),configuration.getTotalPrice(), configuration.getCreatedAt(), configuration.isOrdered());

    }






}
