package com.shipment.logistics.controller;

import com.shipment.logistics.dto.AuthResponse;
import com.shipment.logistics.dto.LoginRequest;
import com.shipment.logistics.entity.Role;
import com.shipment.logistics.entity.User;
import com.shipment.logistics.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody User user) {

        if (user.getRole() == null) {

            user.setRole(Role.CLIENT);
        }

        User savedUser =
                authService.register(user);

        return new ResponseEntity<>(
                savedUser,
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request) {

        String token =
                authService.login(request);

        if (token == null) {

            return new ResponseEntity<>(
                    "Invalid email or password",
                    HttpStatus.UNAUTHORIZED);
        }

        User user =
                authService.getUserByEmail(
                        request.getEmail());

        AuthResponse response =
                new AuthResponse(
                        token,
                        user.getRole().name(),
                        user.getUserId(),
                        user.getName());

        return ResponseEntity.ok(response);
    }
}