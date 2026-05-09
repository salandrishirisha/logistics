package com.shipment.logistics.controller;

import com.shipment.logistics.entity.Shipment;
import com.shipment.logistics.service.ShipmentService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
@CrossOrigin("*")
public class ClientController {

    @Autowired
    private ShipmentService shipmentService;

    // ================= CREATE SHIPMENT =================

    @PostMapping("/create-shipment")
    public Shipment createShipment(

            @RequestBody Shipment shipment,

            @RequestParam Long clientId) {

        return shipmentService
                .createShipment(
                        shipment,
                        clientId);
    }

    // ================= GET MY SHIPMENTS =================

    @GetMapping("/my-shipments")
    public Page<Shipment> getMyShipments(

            @RequestParam Long clientId,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size) {

        return shipmentService
                .getClientShipments(
                        clientId,
                        page,
                        size);
    }

    @PutMapping("/update-shipment/{shipmentId}")
    public Shipment updateShipment(

            @PathVariable Long shipmentId,

            @RequestBody Shipment shipment) {

        return shipmentService
                .updateClientShipment(
                        shipmentId,
                        shipment);
    }

    @DeleteMapping("/delete-shipment/{shipmentId}")
    public String deleteShipment(
            @PathVariable Long shipmentId) {

        return shipmentService
                .deleteClientShipment(
                        shipmentId);
    }
}