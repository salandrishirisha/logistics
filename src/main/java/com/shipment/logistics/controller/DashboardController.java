package com.shipment.logistics.controller;

import com.shipment.logistics.dto.DashboardResponse;
import com.shipment.logistics.entity.Role;

import com.shipment.logistics.repository.ShipmentRepository;
import com.shipment.logistics.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin("*")
public class DashboardController {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private UserRepository userRepository;

    // ================= ADMIN DASHBOARD =================

    @GetMapping("/admin")
    public DashboardResponse adminDashboard() {

        DashboardResponse response =
                new DashboardResponse();

        response.setTotalShipments(
                shipmentRepository.count());

        response.setPendingShipments(
                shipmentRepository
                        .countByStatus("PENDING"));

        response.setApprovedShipments(
                shipmentRepository
                        .countByStatus("APPROVED"));

        response.setDeliveredShipments(
                shipmentRepository
                        .countByStatus("DELIVERED"));

        response.setEmployeeCount(
                userRepository.countByRole(
                        Role.EMPLOYEE));

        response.setClientCount(
                userRepository.countByRole(
                        Role.CLIENT));

        return response;
    }

    // ================= CLIENT DASHBOARD =================

    @GetMapping("/client/{clientId}")
    public DashboardResponse clientDashboard(
            @PathVariable Long clientId) {

        DashboardResponse response =
                new DashboardResponse();

        response.setTotalShipments(
                shipmentRepository
                        .countByClient_UserId(
                                clientId));

        response.setPendingShipments(
                shipmentRepository
                        .countByClient_UserIdAndStatus(
                                clientId,
                                "PENDING"));

        response.setApprovedShipments(
                shipmentRepository
                        .countByClient_UserIdAndStatus(
                                clientId,
                                "APPROVED"));

        response.setDeliveredShipments(
                shipmentRepository
                        .countByClient_UserIdAndStatus(
                                clientId,
                                "DELIVERED"));

        return response;
    }

    // ================= EMPLOYEE DASHBOARD =================

    @GetMapping("/employee/{employeeId}")
    public DashboardResponse employeeDashboard(
            @PathVariable Long employeeId) {

        DashboardResponse response =
                new DashboardResponse();

        response.setTotalShipments(
                shipmentRepository
                        .countByAssignedEmployee_UserId(
                                employeeId));

        response.setDeliveredShipments(
                shipmentRepository
                        .countByAssignedEmployee_UserIdAndStatus(
                                employeeId,
                                "DELIVERED"));

        response.setPendingShipments(
                shipmentRepository
                        .countByAssignedEmployee_UserIdAndStatus(
                                employeeId,
                                "ASSIGNED"));

        return response;
    }
}