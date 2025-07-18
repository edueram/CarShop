package com.errami.mics.orderservice.config;

/** Wegen Serializierung von Date
 * Dependencies:   jackson-databind  &&  jackson-datatype-jsr310
 * Als Bean (Configuration) wird gestartet und von KafkaConsumerConfig verwendet
 * */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
}
