package com.example.SpringConcepts.controller;

import com.example.SpringConcepts.Util.JwtUtil;
import com.example.SpringConcepts.entity.Users;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class LoginController {
    final private AuthenticationManager authenticationManager;
    final private JwtUtil jwtUtil;
    public LoginController(AuthenticationManager authenticationManager,JwtUtil jwtUtil){
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }
    @PostMapping("/login")
    public String login(@RequestBody Users users){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            users.getUsername(),
                            users.getPassword()
                    )
            );
            return jwtUtil.generateToken(users.getUsername());
        } catch (BadCredentialsException e) {
            return "Invalid username or password";
        }
    }
}
