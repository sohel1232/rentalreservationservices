package com.abg.rentalreservationservices.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingUpdationRequest {

    private Long reservationId;
    private String reserverName;
    private String phoneNumber;
    private String pickUpAddress;
    private String dropOffAddress;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

}
