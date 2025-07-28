package com.errami.mics.notificationservice.listener;


import com.errami.mics.notificationservice.model.OrderCreatedEvent;
import com.errami.mics.notificationservice.service.EmailNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderEventListener {

    private final EmailNotificationService emailNotificationService;

    @KafkaListener(topics = "order.created", groupId = "notification-group", containerFactory = "kafkaListenerContainerFactory")
    public void handleOrderCreated(OrderCreatedEvent event) {
        log.info("Empfangenes Kafka-Event: {}", event);
       emailNotificationService.sendEmail(event);
        // notificationService.sendSms(event); // später
        // notificationService.sendWhatsapp(event); // später
    }
}