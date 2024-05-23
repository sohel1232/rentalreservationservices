package com.abg.rentalreservationservices.service;

import com.abg.rentalreservationservices.entity.Reservation;
import com.abg.rentalreservationservices.responseDTO.AvailableCarsResponse;
import org.springframework.security.core.Authentication;
import requestDTO.BookingSuccessResponse;
import requestDTO.ReservationRequest;

import java.util.List;

public interface CarRentalService {
    BookingSuccessResponse reserveCar(Long carId, ReservationRequest reservationRequest, Authentication authentication);

    List<AvailableCarsResponse> getAvailableCars(ReservationRequest reservationRequest);
}
