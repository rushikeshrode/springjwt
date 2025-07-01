package com.rush.springjwt.service;

import com.rush.springjwt.dto.AuthResponse;
import com.rush.springjwt.dto.LoginRequest;
import com.rush.springjwt.dto.RegisterRequest;
import com.rush.springjwt.entity.User;
import com.rush.springjwt.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Component
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtService jwtService;

    public String register(RegisterRequest registerRequest){

        boolean usernameExist = userRepo.existsByUsername(registerRequest.getUsername());
        if (usernameExist)
            return "username: " + registerRequest.getUsername() + " already taken";

        User newUser = User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .roles(registerRequest.getRoles())
                .enabled(true)
                .build();

        userRepo.save(newUser);
        return "User: " + newUser.getUsername() + " Register Successfully";
    }

    public AuthResponse validateLogin(LoginRequest loginRequest){

        User user = userRepo.findByUsername(loginRequest.getUsername()).orElseThrow(() -> new RuntimeException("User not Found."));

        System.out.println("user found: " + user.getUsername());
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            String token = jwtService.generateToken(loginRequest.getUsername());
            return new AuthResponse("Login Success", token);
        }else{
            return new AuthResponse("Invalid Login Credentials!!", null);
        }
    }


}
