package com.example.taskmanager.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
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
