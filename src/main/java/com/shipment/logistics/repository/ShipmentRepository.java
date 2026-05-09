package com.shipment.logistics.repository;

import com.shipment.logistics.entity.Shipment;
import com.shipment.logistics.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository
        extends JpaRepository<Shipment, Long> {

    Page<Shipment> findByItemNameContainingIgnoreCase(
            String keyword,
            Pageable pageable);

    Page<Shipment> findByClient(
            User client,
            Pageable pageable);

    Page<Shipment> findByAssignedEmployee(
            User employee,
            Pageable pageable);


    Page<Shipment> findByClient_UserId(
            Long clientId,
            Pageable pageable);
    long countByStatus(String status);

    long countByClient(User client);

    long countByClientAndStatus(
            User client,
            String status);

    long countByAssignedEmployee(User employee);

    long countByAssignedEmployeeAndStatus(
            User employee,
            String status);


    long countByAssignedEmployeeNameAndStatus(String employeeName, String inProgress);

    long countByAssignedEmployeeName(String employeeName);

    long countByClientNameAndStatus(String clientName, String pending);

    long countByClientName(String clientName);

    long countByClient_UserId(Long clientId);

    long countByClient_UserIdAndStatus(
            Long clientId,
            String status);


    long countByAssignedEmployee_UserIdAndStatus(Long employeeId, String assigned);

    long countByAssignedEmployee_UserId(Long employeeId);
}