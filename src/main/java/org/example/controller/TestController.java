package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/test")
public class TestController {

    @GetMapping
    public String noAuth(){
        return "Nothing to see here";
    }

    @GetMapping("/with-auth")
    public String withAuth(){
        return "Hello there!";
    }
}
