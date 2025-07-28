package com.errami.mics.apigateway.config;

import org.springframework.core.env.Environment;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

// Diese Klasse eingefügt bei der Deployment damit geprüft wird welche Profil in Wirklichkeit läuft :-()

@Component
public class ProfileVerifier {

    private final Environment environment;

    public ProfileVerifier(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void verifyProfile() {
        String[] activeProfiles = environment.getActiveProfiles();
        System.out.println("👉 Aktive Profile: " + String.join(", ", activeProfiles));
    }
}
