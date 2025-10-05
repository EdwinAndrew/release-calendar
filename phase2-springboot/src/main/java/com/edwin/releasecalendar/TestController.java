package com.edwin.releasecalendar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String hello() {
        return "Release Calendar API is running!";
    }

    @GetMapping("/test")
    public String test() {
        return "Test endpoint works!";
    }
}
