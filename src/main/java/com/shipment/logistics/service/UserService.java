package com.shipment.logistics.service;

import com.shipment.logistics.entity.Role;
import com.shipment.logistics.entity.User;
import com.shipment.logistics.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {

        return userRepository.save(user);
    }

    public List<User> getEmployees() {

        return userRepository
                .findByRole(Role.EMPLOYEE);
    }

    public List<User> getClients() {

        return userRepository
                .findByRole(Role.CLIENT);
    }

    public User updateUser(
            Long id,
            User updatedUser) {

        User user =
                userRepository.findById(id)
                        .orElse(null);

        if (user == null) {
            return null;
        }

        user.setName(updatedUser.getName());

        user.setEmail(updatedUser.getEmail());

        user.setPassword(
                updatedUser.getPassword());

        return userRepository.save(user);
    }

    public String deleteUser(Long id) {

        userRepository.deleteById(id);

        return "User deleted successfully";
    }
}