package com.shipment.logistics.service;

import com.shipment.logistics.dto.LoginRequest;
import com.shipment.logistics.entity.User;
import com.shipment.logistics.repository.UserRepository;
import com.shipment.logistics.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public User register(User user) {

        user.setPassword(
                passwordEncoder.encode(
                        user.getPassword()));

        return userRepository.save(user);
    }

    public String login(LoginRequest request) {

        User user =
                userRepository.findByEmail(
                                request.getEmail())
                        .orElse(null);

        if (user == null) {
            return null;
        }

        boolean passwordMatches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword());

        if (!passwordMatches) {
            return null;
        }

        return jwtUtil.generateToken(
                user.getEmail());
    }

    public User getUserByEmail(
            String email) {

        return userRepository.findByEmail(email)
                .orElse(null);
    }
}