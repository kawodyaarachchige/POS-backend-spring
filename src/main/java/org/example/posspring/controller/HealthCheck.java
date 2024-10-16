package org.example.posspring.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/healthcheck")
public class HealthCheck {
    @GetMapping
    public String healthCheck() {
        return "Server is up and running";
    }
}
