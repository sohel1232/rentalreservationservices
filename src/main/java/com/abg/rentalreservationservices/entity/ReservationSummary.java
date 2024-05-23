package com.abg.rentalreservationservices.entity;

import com.abg.rentalreservationservices.encryption.EncryptionUtil;
import lombok.Builder;
import lombok.Data;

import javax.crypto.SecretKey;
import java.math.BigDecimal;

@Builder
@Data
public class ReservationSummary {
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

    public void encryptFields(SecretKey key) throws Exception {
        System.out.println("HI 6");
        this.reserverPhoneNumber = EncryptionUtil.encrypt(this.reserverPhoneNumber, key);
        this.reserverDrivingLicenceNumber = EncryptionUtil.encrypt(this.reserverDrivingLicenceNumber, key);
        this.reserverEmail = EncryptionUtil.encrypt(this.reserverEmail,key);

        this.carPlate = EncryptionUtil.encrypt(this.carPlate, key);

        this.pickUpAddress = EncryptionUtil.encrypt(this.pickUpAddress,key);
        this.dropOffAddress = EncryptionUtil.encrypt(this.dropOffAddress,key);
        System.out.println("HI 7");
    }
}
