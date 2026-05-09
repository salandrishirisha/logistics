package com.shipment.logistics.service;

import com.shipment.logistics.entity.Role;
import com.shipment.logistics.entity.Shipment;
import com.shipment.logistics.entity.User;
import com.shipment.logistics.repository.ShipmentRepository;
import com.shipment.logistics.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private UserRepository userRepository;

    public Shipment createShipment(
            Shipment shipment,
            Long clientId) {

        User client =
                userRepository.findById(clientId)
                        .orElse(null);

        if (client == null ||
                client.getRole() != Role.CLIENT) {

            return null;
        }

        shipment.setClient(client);

        shipment.setStatus("PENDING");

        shipment.setApproved(false);

        shipment.setRejected(false);

        return shipmentRepository.save(shipment);
    }

    public Page<Shipment> getAllShipments(
            int page,
            int size,
            String keyword) {

        Pageable pageable =
                PageRequest.of(page, size);

        if (keyword != null &&
                !keyword.isEmpty()) {

            return shipmentRepository
                    .findByItemNameContainingIgnoreCase(
                            keyword,
                            pageable);
        }

        return shipmentRepository.findAll(pageable);
    }

    public Shipment getShipmentById(Long id) {

        return shipmentRepository.findById(id)
                .orElse(null);
    }

    public Shipment updateShipment(
            Long id,
            Shipment updatedShipment) {

        Shipment shipment =
                shipmentRepository.findById(id)
                        .orElse(null);

        if (shipment == null) {
            return null;
        }

        shipment.setShipmentType(
                updatedShipment.getShipmentType());

        shipment.setOriginCountry(
                updatedShipment.getOriginCountry());

        shipment.setDestinationCountry(
                updatedShipment.getDestinationCountry());

        shipment.setPickupAddress(
                updatedShipment.getPickupAddress());

        shipment.setDeliveryAddress(
                updatedShipment.getDeliveryAddress());

        shipment.setItemName(
                updatedShipment.getItemName());

        shipment.setQuantity(
                updatedShipment.getQuantity());

        shipment.setWeight(
                updatedShipment.getWeight());

        shipment.setTransportMode(
                updatedShipment.getTransportMode());

        return shipmentRepository.save(shipment);
    }

    public String deleteShipment(Long id) {

        shipmentRepository.deleteById(id);

        return "Shipment deleted successfully";
    }

    public Shipment approveShipment(Long shipmentId) {

        Shipment shipment =
                shipmentRepository.findById(shipmentId)
                        .orElse(null);

        if (shipment == null) {
            return null;
        }

        shipment.setApproved(true);

        shipment.setRejected(false);

        shipment.setStatus("APPROVED");

        return shipmentRepository.save(shipment);
    }

    public Shipment rejectShipment(Long shipmentId) {

        Shipment shipment =
                shipmentRepository.findById(shipmentId)
                        .orElse(null);

        if (shipment == null) {
            return null;
        }

        shipment.setRejected(true);

        shipment.setApproved(false);

        shipment.setStatus("REJECTED");

        return shipmentRepository.save(shipment);
    }

    public Shipment assignEmployee(
            Long shipmentId,
            Long employeeId) {

        Shipment shipment =
                shipmentRepository.findById(shipmentId)
                        .orElse(null);

        User employee =
                userRepository.findById(employeeId)
                        .orElse(null);

        if (shipment == null ||
                employee == null ||
                employee.getRole() != Role.EMPLOYEE) {

            return null;
        }

        shipment.setAssignedEmployee(employee);

        shipment.setStatus("ASSIGNED");

        return shipmentRepository.save(shipment);
    }

    public Page<Shipment> getEmployeeShipments(
            Long employeeId,
            int page,
            int size) {

        User employee =
                userRepository.findById(employeeId)
                        .orElse(null);

        Pageable pageable =
                PageRequest.of(page, size);

        return shipmentRepository
                .findByAssignedEmployee(
                        employee,
                        pageable);
    }
    public Shipment updateShipmentStatus(

            Long shipmentId,
            String status,
            String remarks) {

        Shipment shipment =
                shipmentRepository.findById(shipmentId)
                        .orElse(null);

        if (shipment == null) {
            return null;
        }

        shipment.setStatus(status);

        shipment.setEmployeeRemarks(remarks);

        return shipmentRepository.save(shipment);
    }

    public Page<Shipment> getClientShipments(
            Long clientId,
            int page,
            int size) {

        Pageable pageable =
                PageRequest.of(page, size);

        return shipmentRepository
                .findByClient_UserId(
                        clientId,
                        pageable);
    }
    public Shipment updateClientShipment(

            Long shipmentId,

            Shipment updatedShipment) {

        Shipment shipment =
                shipmentRepository.findById(
                                shipmentId)
                        .orElse(null);

        if (shipment == null) {
            return null;
        }

        shipment.setShipmentType(
                updatedShipment.getShipmentType());

        shipment.setOriginCountry(
                updatedShipment.getOriginCountry());

        shipment.setDestinationCountry(
                updatedShipment.getDestinationCountry());

        shipment.setPickupAddress(
                updatedShipment.getPickupAddress());

        shipment.setDeliveryAddress(
                updatedShipment.getDeliveryAddress());

        shipment.setItemName(
                updatedShipment.getItemName());

        shipment.setQuantity(
                updatedShipment.getQuantity());

        shipment.setWeight(
                updatedShipment.getWeight());

        shipment.setTransportMode(
                updatedShipment.getTransportMode());

        return shipmentRepository.save(shipment);
    }

    public String deleteClientShipment(
            Long shipmentId) {

        Shipment shipment =
                shipmentRepository.findById(
                                shipmentId)
                        .orElse(null);

        if (shipment == null) {
            return "Shipment not found";
        }

        shipmentRepository.delete(shipment);

        return "Shipment deleted successfully";
    }
}