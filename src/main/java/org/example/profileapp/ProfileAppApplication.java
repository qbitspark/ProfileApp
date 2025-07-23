// Main Application Class
package org.example.profileapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
@RestController
public class ProfileAppApplication {

    @Value("${spring.profiles.active:default}")
    private String activeProfile;

    @GetMapping("/profile")
    public String getCurrentProfile() {
        return "Current active profile: " + activeProfile;
    }

    @GetMapping("/")
    public String home() {
        return "Spring Boot Profile Test App - Check /profile endpoint";
    }

    public static void main(String[] args) {
        SpringApplication.run(ProfileAppApplication.class, args);
    }
}