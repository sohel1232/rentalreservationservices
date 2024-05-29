package com.abg.rentalreservationservices.entity;

import com.abg.rentalreservationservices.encryption.EncryptionUtil;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import javax.crypto.SecretKey;
import java.math.BigDecimal;

@Builder
@Data
public class ReservationSummary {
    private Long reservationId;

    private String reserverName;
    private String reserverEmail;
    private String reserverPhoneNumber;
    private String reserverDrivingLicenceNumber;

    private String carName;
    private String carPlate;
    private String carType;
    private Integer carSeatingCapacity;

    private String sourceCity;
    private String destinationCity;
    private String pickUpAddress;
    private String dropOffAddress;
    private String startDateTime;
    private String endDateTime;
    private BigDecimal reservationAmount;

    public void encryptFields() throws Exception {
        this.reserverName = EncryptionUtil.encrypt(this.reserverName);
        this.reserverPhoneNumber = EncryptionUtil.encrypt(this.reserverPhoneNumber);
        this.reserverDrivingLicenceNumber = EncryptionUtil.encrypt(this.reserverDrivingLicenceNumber);
        this.reserverEmail = EncryptionUtil.encrypt(this.reserverEmail);
    }
}
