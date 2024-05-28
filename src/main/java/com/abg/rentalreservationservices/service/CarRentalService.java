package com.abg.rentalreservationservices.service;

import com.abg.rentalreservationservices.requestDTO.BookingUpdationRequest;
import com.abg.rentalreservationservices.responseDTO.AvailableCarsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import com.abg.rentalreservationservices.responseDTO.BookingSuccessResponse;
import com.abg.rentalreservationservices.requestDTO.ReservationRequest;

import java.util.List;

public interface CarRentalService {
    ResponseEntity<BookingSuccessResponse> reserveCar(Long carId, ReservationRequest reservationRequest, Authentication authentication) throws Exception;

    ResponseEntity<List<AvailableCarsResponse>> getAvailableCars(ReservationRequest reservationRequest);

    ResponseEntity<BookingSuccessResponse> updateReservation(BookingUpdationRequest bookingUpdationRequest) throws Exception;
}
