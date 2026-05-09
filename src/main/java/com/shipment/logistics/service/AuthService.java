package com.shipment.logistics.service;

import com.shipment.logistics.dto.LoginRequest;
import com.shipment.logistics.entity.User;
import com.shipment.logistics.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public User register(User user) {

        return userRepository.save(user);
    }

    public User login(LoginRequest request) {

        return userRepository.findByEmail(request.getEmail())
                .filter(user ->
                        user.getPassword()
                                .equals(request.getPassword()))
                .orElse(null);
    }
}