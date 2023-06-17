package com.Lvoquang.sv7.services;

import com.Lvoquang.sv7.security.auth.AuthenticationRequest;
import com.Lvoquang.sv7.security.auth.AuthenticationRestponse;
import com.Lvoquang.sv7.security.auth.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public interface AuthenService {

    @PostMapping("/register")
    public ResponseEntity<AuthenticationRestponse> register(@RequestBody RegisterRequest request);

    @PostMapping("/login")
    public ResponseEntity<AuthenticationRestponse> login(@RequestBody AuthenticationRequest request);


}
