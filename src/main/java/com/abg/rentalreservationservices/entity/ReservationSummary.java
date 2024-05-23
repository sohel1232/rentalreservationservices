package com.abg.rentalreservationservices.entity;

import lombok.Builder;
import java.math.BigDecimal;

@Builder
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
}
