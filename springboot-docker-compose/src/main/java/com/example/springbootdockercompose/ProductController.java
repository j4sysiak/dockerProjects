package com.example.springbootdockercompose;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping
    public String getStatus() {
        return "Application is up and running";
    }
}

