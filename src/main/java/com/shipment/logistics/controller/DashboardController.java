package com.shipment.logistics.controller;

import com.shipment.logistics.dto.DashboardResponse;
import com.shipment.logistics.repository.ShipmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin("*")
public class DashboardController {

    @Autowired
    private ShipmentRepository shipmentRepository;

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

        return response;
    }

    @GetMapping("/client")
    public DashboardResponse clientDashboard(
            @RequestParam String clientName) {

        DashboardResponse response =
                new DashboardResponse();

        response.setTotalShipments(
                shipmentRepository
                        .countByClientName(
                                clientName));

        response.setPendingShipments(
                shipmentRepository
                        .countByClientNameAndStatus(
                                clientName,
                                "PENDING"));

        response.setApprovedShipments(
                shipmentRepository
                        .countByClientNameAndStatus(
                                clientName,
                                "APPROVED"));

        return response;
    }

    @GetMapping("/employee")
    public DashboardResponse employeeDashboard(
            @RequestParam String employeeName) {

        DashboardResponse response =
                new DashboardResponse();

        response.setTotalShipments(
                shipmentRepository
                        .countByAssignedEmployeeName(
                                employeeName));

        response.setDeliveredShipments(
                shipmentRepository
                        .countByAssignedEmployeeNameAndStatus(
                                employeeName,
                                "DELIVERED"));

        response.setPendingShipments(
                shipmentRepository
                        .countByAssignedEmployeeNameAndStatus(
                                employeeName,
                                "IN_PROGRESS"));

        return response;
    }
}