package com.errami.mics.notificationservice.service;

import com.errami.mics.notificationservice.model.OrderCreatedEvent;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final JavaMailSender mailSender;
    public void sendEmail(OrderCreatedEvent event) {
        try {
            var message = mailSender.createMimeMessage();
            var helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("billing@errami.info");                  // Sichtbare Absenderadresse
            helper.setReplyTo("contact@errami.info");              // Antworten gehen an diese Adresse
            helper.setTo(event.getUserEmail());
            helper.setSubject("Ihre Bestellung bei Car Config");

            String content = "<p>Vielen Dank für Ihre Bestellung!</p>" +
                    "<p><strong>Bestellungsnummer:</strong> " + event.getOrderId() + "</p>" +
                    "<p><strong>Konfigurationsnummer: </strong> " + event.getConfigurationId() + "</p>" +
                    "<p><strong>Gesamtpreis:</strong> " + event.getFinalPrice() + " €</p>" +
                    "<p><strong>Bestelldatum:</strong> " + event.getOrderDate() + "</p>";

            helper.setText(content, true); // true = HTML

            mailSender.send(message);
            log.info("E-Mail erfolgreich gesendet an {}", event.getUserEmail());

        } catch (MessagingException e) {
            log.error("Fehler beim E-Mail-Versand: {}", e.getMessage());
        }
    }

    // SMS / WhatsApp Methoden folgen
}