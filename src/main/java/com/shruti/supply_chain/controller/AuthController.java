package com.shruti.supply_chain.controller;


import com.shruti.supply_chain.dto.AuthResponse;
import com.shruti.supply_chain.dto.LoginRequest;
import com.shruti.supply_chain.dto.RegisterRequest;
import com.shruti.supply_chain.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // REGISTER
    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }
    // LOGIN
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

}
