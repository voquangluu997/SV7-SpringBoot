package com.Lvoquang.sv7.services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface UserService {

    @GetMapping("/home")
    public String home();

    @GetMapping("/admin")
    public String admin();

}
