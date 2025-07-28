package com.errami.mics.notificationservice.service;

import com.errami.mics.notificationservice.model.OrderCreatedEvent;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsNotificationService {

    @Value("${twilio.phone_number}")
    private String fromNumber;

    public void sendSms(OrderCreatedEvent event) {
        String to= event.getTelefonnumber();
        String message = "<p>Vielen Dank für Ihre Bestellung!</p>" +
                "<p><strong>Bestellungsnummer:</strong> " + event.getOrderId() + "</p>" +
                "<p><strong>Konfigurationsnummer: </strong> " + event.getConfigurationId() + "</p>" +
                "<p><strong>Gesamtpreis:</strong> " + event.getFinalPrice() + " €</p>" +
                "<p><strong>Bestelldatum:</strong> " + event.getOrderDate() + "</p>";

        Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(fromNumber),
                message
        ).create();
    }
}

