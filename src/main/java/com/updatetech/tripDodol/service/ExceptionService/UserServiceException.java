package com.updatetech.tripDodol.service.ExceptionService;

public class UserServiceException extends RuntimeException{
    public static final long serialVersionUID= 1212L;

    public UserServiceException(String message) {
        super(message);
    }
}
