package com.updatetech.tripDodol.utility;

import com.updatetech.tripDodol.model.packageModel.PackageBookingStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PackageBookingStatusConverter implements AttributeConverter<PackageBookingStatus, String> {
    @Override
    public String convertToDatabaseColumn(PackageBookingStatus status) {
        if (status == null){
            return null;
        }
        return status.getStatus();
    }

    @Override
    public PackageBookingStatus convertToEntityAttribute(String status) {
        if (status == null){
            return null;
        }
        try {
            return PackageBookingStatus.valueOf(status);
        }catch (IllegalArgumentException e){
            return null;
        }
    }
}

