package com.updatetech.tripDodol.utility;

import com.updatetech.tripDodol.model.Status;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {
    @Override
    public String convertToDatabaseColumn(Status status) {
        if (status == null){
            return null;
        }
        return status.getStatus();
    }

    @Override
    public Status convertToEntityAttribute(String status) {
        if (status == null){
            return null;
        }
        try {
            return Status.valueOf(status);
        }catch (IllegalArgumentException e){
            return null;
        }
    }
}
