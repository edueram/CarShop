package com.errami.mics.notificationservice.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WhatsappNotificationService {

    @Value("${twilio.phone_number}")
    private String fromWhatsApp;

    public void sendWhatsApp(String to, String message) {
        Message.creator(
                new PhoneNumber("whatsapp:" + to),
                new PhoneNumber("whatsapp:" + fromWhatsApp),
                message
        ).create();
    }
}
