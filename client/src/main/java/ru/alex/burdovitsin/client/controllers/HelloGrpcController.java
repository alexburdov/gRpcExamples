package ru.alex.burdovitsin.client.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alex.burdovitsin.client.clients.HelloGrpcClient;

@RestController
@RequestMapping("/")
public class HelloGrpcController {

    private final HelloGrpcClient client;

    public HelloGrpcController(HelloGrpcClient client) {
        this.client = client;
    }

    @GetMapping("/echo")
    public String echo() {
        return client.sayHello("input");
    }

    @GetMapping("/say/{text}")
    public String greeting(@PathVariable("text") String text) {
        return "ANSWER: " + client.sayHello(text);
    }
}
