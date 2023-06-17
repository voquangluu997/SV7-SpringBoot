package com.Lvoquang.sv7.services;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    public String home() {
        return "home";
    }

    public String admin() {
        return "admin";
    }
}
