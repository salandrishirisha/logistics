package com.shipment.logistics.controller;

import com.shipment.logistics.entity.Role;
import com.shipment.logistics.entity.Shipment;
import com.shipment.logistics.entity.User;
import com.shipment.logistics.service.ShipmentService;
import com.shipment.logistics.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private ShipmentService shipmentService;

    @Autowired
    private UserService userService;

    @PutMapping("/approve/{shipmentId}")
    public Shipment approveShipment(
            @PathVariable Long shipmentId) {

        return shipmentService
                .approveShipment(shipmentId);
    }

    @PutMapping("/reject/{shipmentId}")
    public Shipment rejectShipment(
            @PathVariable Long shipmentId) {

        return shipmentService
                .rejectShipment(shipmentId);
    }

    @PutMapping("/assign")
    public Shipment assignEmployee(

            @RequestParam Long shipmentId,

            @RequestParam Long employeeId) {

        return shipmentService
                .assignEmployee(
                        shipmentId,
                        employeeId);
    }

    @PostMapping("/create-employee")
    public User createEmployee(
            @RequestBody User user) {

        user.setRole(Role.EMPLOYEE);

        return userService.createUser(user);
    }

    @PostMapping("/create-client")
    public User createClient(
            @RequestBody User user) {

        user.setRole(Role.CLIENT);

        return userService.createUser(user);
    }

    @GetMapping("/employees")
    public List<User> getEmployees() {

        return userService.getEmployees();
    }

    @GetMapping("/clients")
    public List<User> getClients() {

        return userService.getClients();
    }

    @PutMapping("/update-user/{id}")
    public User updateUser(
            @PathVariable Long id,

            @RequestBody User user) {

        return userService
                .updateUser(id, user);
    }

    @DeleteMapping("/delete-user/{id}")
    public String deleteUser(
            @PathVariable Long id) {

        return userService.deleteUser(id);
    }
}