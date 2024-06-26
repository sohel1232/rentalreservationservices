package com.abg.rentalreservationservices.service;

import com.abg.rentalreservationservices.entity.Reservation;
import com.abg.rentalreservationservices.entity.ReservationSummary;
import com.abg.rentalreservationservices.requestDTO.BookingUpdationRequest;
import com.abg.rentalreservationservices.responseDTO.ReservationCancellationSuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import com.abg.rentalreservationservices.requestDTO.ReservationRequest;

public interface ReservationService {
    Reservation makeNewReservation(Long carId, ReservationRequest reservationRequest, Authentication authentication);

    ReservationSummary generateReservationSummary(Reservation reservation);

    Reservation updateReservation(Long reservationId, BookingUpdationRequest bookingUpdationRequest);

    Reservation getReservationById(long reservationId);

    ResponseEntity<ReservationCancellationSuccessResponse> cancelReservation(Long reservationId);
}
