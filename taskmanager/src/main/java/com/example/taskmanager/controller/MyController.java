package com.example.taskmanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a PUBLIC endpoint — no login needed.";
    }

    @GetMapping("/user")
    public String userEndpoint() {
        return "This is a USER endpoint — only USER or ADMIN can access this.";
    }

    @GetMapping("/admin")
    public String adminEndpoint() {
        return "This is an ADMIN endpoint — only ADMIN can access this.";
    }
}
