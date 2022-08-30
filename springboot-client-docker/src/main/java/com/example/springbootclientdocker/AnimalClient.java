package com.example.springbootclientdocker;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//@Controller
@RestController
public class AnimalClient {

    //end point
    @GetMapping("/showForGui")
    @ResponseBody
    public Animal[] get() {
        RestTemplate restTemplate = new RestTemplate();
		/*  uruchomienie lokalne
        ResponseEntity<Animal[]> exchange = restTemplate.exchange("http://localhost:9090/animals",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                Animal[].class); */
				
		/*  uruchomienie dockerowe */		
		ResponseEntity<Animal[]> exchange = restTemplate.exchange("http://api:9090/animals",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                Animal[].class);

        Animal[] body = exchange.getBody();
        return body;
    }

}
