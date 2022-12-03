package com.updatetech.tripDodol.model.Exception;

public enum ErrorMessages {

    USER_NOT_FOUND("User not found!"),
    INVALID_TOKEN("Invalid token"),
    TOKEN_TIME_EXPIRED("Token time expired");


    private String errorMessages;

    ErrorMessages(String errorMessages) {
        this.errorMessages = errorMessages;
    }


    public String getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(String errorMessages) {
        this.errorMessages = errorMessages;
    }
}
