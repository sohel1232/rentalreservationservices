package com.abg.rentalreservationservices.manager;

import com.abg.rentalreservationservices.entity.Car;
import com.abg.rentalreservationservices.entity.Reservation;
import com.abg.rentalreservationservices.entity.ServicableCity;
import com.abg.rentalreservationservices.entity.User;
import com.abg.rentalreservationservices.repository.ReservationRepository;
import com.abg.rentalreservationservices.responseDTO.AvailableCarsResponse;
import com.abg.rentalreservationservices.service.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import requestDTO.BookingSuccessResponse;
import requestDTO.ReservationRequest;

import java.util.List;

@Service
@AllArgsConstructor
public class CarRentalManager implements CarRentalService {

    private final ReservationService reservationService;
    private CarService carService;
    private final ResponseManager responseManager;

    @Override
    public BookingSuccessResponse reserveCar(Long carId, ReservationRequest reservationRequest, Authentication authentication) {
        Reservation reservation = reservationService.makeNewReservation(carId,reservationRequest,authentication);
        return responseManager.buildSuccessBookingResponse(reservation);
    }

    @Override
    public List<AvailableCarsResponse> getAvailableCars(ReservationRequest reservationRequest) {
        List<Car> availableCars = carService.getAvailableCars(reservationRequest);
        return responseManager.buildAvailableCarsResponse(availableCars);
    }
}
