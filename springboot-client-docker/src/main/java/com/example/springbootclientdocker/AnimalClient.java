package com.example.springbootclientdocker;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class AnimalClient {

    //end point
    @GetMapping("/showForGui")
    public Animal[] get() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Animal[]> exchange = restTemplate.exchange("http://localhost:9090/animals",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                Animal[].class);

        Animal[] body = exchange.getBody();
        return body;
    }

}
