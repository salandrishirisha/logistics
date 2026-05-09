package com.shipment.logistics.dto;

public class DashboardResponse {

    private long totalShipments;

    private long pendingShipments;

    private long approvedShipments;

    private long deliveredShipments;

    public DashboardResponse() {
    }

    public long getTotalShipments() {
        return totalShipments;
    }

    public void setTotalShipments(long totalShipments) {
        this.totalShipments = totalShipments;
    }

    public long getPendingShipments() {
        return pendingShipments;
    }

    public void setPendingShipments(long pendingShipments) {
        this.pendingShipments = pendingShipments;
    }

    public long getApprovedShipments() {
        return approvedShipments;
    }

    public void setApprovedShipments(long approvedShipments) {
        this.approvedShipments = approvedShipments;
    }

    public long getDeliveredShipments() {
        return deliveredShipments;
    }

    public void setDeliveredShipments(long deliveredShipments) {
        this.deliveredShipments = deliveredShipments;
    }
}