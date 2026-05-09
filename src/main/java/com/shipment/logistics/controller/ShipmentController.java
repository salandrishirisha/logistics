package com.shipment.logistics.controller;

import com.shipment.logistics.entity.Shipment;
import com.shipment.logistics.service.ShipmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipments")
@CrossOrigin("*")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @PostMapping
    public Shipment createShipment(
            @RequestBody Shipment shipment,
            @RequestParam Long clientId) {

        return shipmentService
                .createShipment(
                        shipment,
                        clientId);
    }

    @GetMapping
    public Page<Shipment> getAllShipments(
            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size,

            @RequestParam(required = false)
            String keyword) {

        return shipmentService
                .getAllShipments(
                        page,
                        size,
                        keyword);
    }

    @GetMapping("/{id}")
    public Shipment getShipmentById(
            @PathVariable Long id) {

        return shipmentService
                .getShipmentById(id);
    }

    @PutMapping("/{id}")
    public Shipment updateShipment(
            @PathVariable Long id,
            @RequestBody Shipment shipment) {

        return shipmentService
                .updateShipment(id, shipment);
    }

    @DeleteMapping("/{id}")
    public String deleteShipment(
            @PathVariable Long id) {

        return shipmentService
                .deleteShipment(id);
    }
}