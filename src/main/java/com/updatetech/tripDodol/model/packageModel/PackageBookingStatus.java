package com.updatetech.tripDodol.model.packageModel;

public enum PackageBookingStatus {

    DEFAULT("DEFAULT"),
    PENDING("PENDING"),
    CONFIRM("CONFIRM");

    private String status;

    private PackageBookingStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
}
