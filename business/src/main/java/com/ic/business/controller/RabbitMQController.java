package com.ic.business.controller;

import com.ic.business.config.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mq")
public class RabbitMQController {

    @Autowired
    private RabbitMQSender rabbitMqSender;

    @GetMapping("/send")
    public void sendMsg() {
        rabbitMqSender.sendMsg("啦啦啦");
    }
}
