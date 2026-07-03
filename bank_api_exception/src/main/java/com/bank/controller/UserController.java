package com.bank.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/user")
    public String getUser(){
        return "user";
    }

    @GetMapping("/public")
    public String publicUser(){
        return "user";
    }
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/profile")
    public String profile(){
        return "profile";
    }
}
