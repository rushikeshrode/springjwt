package com.rush.springjwt.Health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @GetMapping("/")
    public String checkHealth(){
        return "Application Status SpringBoot-JWT : OKK";
    }
}
