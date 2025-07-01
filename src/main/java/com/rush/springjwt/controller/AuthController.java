package com.rush.springjwt.controller;

import com.rush.springjwt.dto.AuthResponse;
import com.rush.springjwt.dto.LoginRequest;
import com.rush.springjwt.dto.RegisterRequest;
import com.rush.springjwt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest){
        String register = authService.register(registerRequest);
        return ResponseEntity.ok(register);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        AuthResponse authResponse = authService.validateLogin(loginRequest);
        return ResponseEntity.ok(authResponse);
    }

}
