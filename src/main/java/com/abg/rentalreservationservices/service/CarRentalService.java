package com.abg.rentalreservationservices.service;

import com.abg.rentalreservationservices.responseDTO.AvailableCarsResponse;
import org.springframework.security.core.Authentication;
import com.abg.rentalreservationservices.responseDTO.BookingSuccessResponse;
import com.abg.rentalreservationservices.requestDTO.ReservationRequest;

import java.util.List;

public interface CarRentalService {
    BookingSuccessResponse reserveCar(Long carId, ReservationRequest reservationRequest, Authentication authentication) throws Exception;

    List<AvailableCarsResponse> getAvailableCars(ReservationRequest reservationRequest);
}
