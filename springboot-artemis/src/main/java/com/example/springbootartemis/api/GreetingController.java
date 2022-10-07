package com.example.springbootartemis.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/greeting")
@Slf4j
public class GreetingController {
    private final JmsTemplate jmsTemplate;

    // http://localhost:8080/greeting/getting
    @GetMapping("/getting")
    public String test() {
        log.info("Send this message to jms queue");
        return "test: GreetingController from GetMapping";
    }


    //   http://localhost:8080/greeting/
    @PostMapping("/")
    public String sendMessage(@RequestBody String message) {
        log.info("Send this message to jms queue: " + message);
//        jmsTemplate.convertAndSend("myqueue2", message);
        return "Message sent " + message;
    }


}
