package com.tech.society.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/public/hello")
    public String publicHello() {
        return "Hello, public!";
    }

    @GetMapping("/private/hello")
    public String privateHello() {
        return "Hello, authenticated user!";
    }
}