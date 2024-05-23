package com.abg.rentalreservationservices.service;

import com.abg.rentalreservationservices.entity.Reservation;
import com.abg.rentalreservationservices.entity.ReservationSummary;
import org.springframework.security.core.Authentication;
import requestDTO.ReservationRequest;

public interface ReservationService {
    Reservation makeNewReservation(Long carId, ReservationRequest reservationRequest, Authentication authentication);

    ReservationSummary generateReservationSummary(Reservation reservation);
}
