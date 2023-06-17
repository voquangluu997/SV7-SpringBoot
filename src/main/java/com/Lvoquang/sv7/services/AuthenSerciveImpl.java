package com.Lvoquang.sv7.services;

import com.Lvoquang.sv7.handler.auth.AuthenticationHandler;
import com.Lvoquang.sv7.security.auth.AuthenticationRequest;
import com.Lvoquang.sv7.security.auth.AuthenticationRestponse;
import com.Lvoquang.sv7.security.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthenSerciveImpl implements AuthenService {

    private final AuthenticationHandler authenticationHandler;


    @Override
    public ResponseEntity<AuthenticationRestponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authenticationHandler.register(registerRequest));
    }

    @Override
    public ResponseEntity<AuthenticationRestponse> login(AuthenticationRequest request) {

        return ResponseEntity.ok(authenticationHandler.login(request));
    }

}
