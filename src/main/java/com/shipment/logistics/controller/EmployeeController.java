package com.shipment.logistics.controller;

import com.shipment.logistics.entity.Shipment;
import com.shipment.logistics.service.ShipmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private ShipmentService shipmentService;
    @GetMapping("/shipments")
    public Page<Shipment> getAssignedShipments(

            @RequestParam Long employeeId,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size) {

        return shipmentService
                .getEmployeeShipments(
                        employeeId,
                        page,
                        size);
    }
    @PutMapping("/update-status/{shipmentId}")
    public Shipment updateShipmentStatus(

            @PathVariable Long shipmentId,

            @RequestParam String status,

            @RequestParam String remarks) {

        return shipmentService
                .updateShipmentStatus(
                        shipmentId,
                        status,
                        remarks);
    }
}