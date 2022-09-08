package com.example.demo12;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @Value("${user}")
    private String user;

    @GetMapping("/test")
    public String get() {
        return "Hello: " + user;
    }
}
