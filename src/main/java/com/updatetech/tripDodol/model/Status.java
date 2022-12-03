package com.updatetech.tripDodol.model;

public enum Status {
    DEFAULT("DEFAULT"),
    PENDING("PENDING"),
    CONFIRM("CONFIRM");

    private String status;

    private Status(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
}
