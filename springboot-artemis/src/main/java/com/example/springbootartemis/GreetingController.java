package com.example.springbootartemis;

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

    // http://localhost:8080/greeting/test
    @GetMapping("/getting")
    public String test() {
//        log.info("Send this message to jms queue");
        return "test: GreetingController from GetMapping";
    }

    @PostMapping("/posting")
    public String sendMessage(@RequestBody String message) {
//        log.info("Send this message to jms queue: " + message);
//        jmsTemplate.convertAndSend("myqueue2", message);
        return "Message sent " + message;
    }


}
