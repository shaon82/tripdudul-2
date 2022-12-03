package com.updatetech.tripDodol.model;

public enum HotelStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    PENDING("PENDING");

    private String status;

    HotelStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
