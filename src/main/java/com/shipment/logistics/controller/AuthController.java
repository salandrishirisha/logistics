package com.shipment.logistics.controller;

import com.shipment.logistics.dto.LoginRequest;
import com.shipment.logistics.entity.Role;
import com.shipment.logistics.entity.User;
import com.shipment.logistics.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User register(
            @RequestBody User user) {

        return authService.register(user);
    }

    @PostMapping("/login")
    public Object login(
            @RequestBody LoginRequest request) {

        User user = authService.login(request);

        if (user != null) {
            return user;
        }

        return "Invalid credentials";
    }

    @PostMapping("/create-admin")
    public User createAdmin() {

        User admin = new User();

        admin.setName("Admin");

        admin.setEmail("admin@gmail.com");

        admin.setPassword("admin123");

        admin.setRole(Role.ADMIN);

        return authService.register(admin);
    }
}