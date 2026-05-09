package com.shipment.logistics.service;

import com.shipment.logistics.entity.Role;
import com.shipment.logistics.repository.ShipmentRepository;
import com.shipment.logistics.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private UserRepository userRepository;

    // ================= ADMIN DASHBOARD =================

    public Map<String, Object> getAdminDashboard() {

        Map<String, Object> response =
                new HashMap<>();

        response.put(
                "totalShipments",
                shipmentRepository.count());

        response.put(
                "pendingShipments",
                shipmentRepository.countByStatus("PENDING"));

        response.put(
                "approvedShipments",
                shipmentRepository.countByStatus("APPROVED"));

        response.put(
                "deliveredShipments",
                shipmentRepository.countByStatus("DELIVERED"));

        response.put(
                "employeeCount",
                userRepository.countByRole(Role.EMPLOYEE));

        response.put(
                "clientCount",
                userRepository.countByRole(Role.CLIENT));

        return response;
    }

    // ================= CLIENT DASHBOARD =================

    public Map<String, Object> getClientDashboard(
            Long clientId) {

        Map<String, Object> response =
                new HashMap<>();

        response.put(
                "myShipments",
                shipmentRepository.countByClient_UserId(clientId));

        response.put(
                "pendingShipments",
                shipmentRepository
                        .countByClient_UserIdAndStatus(
                                clientId,
                                "PENDING"));

        response.put(
                "approvedShipments",
                shipmentRepository
                        .countByClient_UserIdAndStatus(
                                clientId,
                                "APPROVED"));

        response.put(
                "deliveredShipments",
                shipmentRepository
                        .countByClient_UserIdAndStatus(
                                clientId,
                                "DELIVERED"));

        return response;
    }

    // ================= EMPLOYEE DASHBOARD =================

    public Map<String, Object> getEmployeeDashboard(
            Long employeeId) {

        Map<String, Object> response =
                new HashMap<>();

        response.put(
                "assignedShipments",
                shipmentRepository.count());

        response.put(
                "completedShipments",
                shipmentRepository.countByStatus("DELIVERED"));

        response.put(
                "pendingVerification",
                shipmentRepository.countByStatus("ASSIGNED"));

        return response;
    }
}