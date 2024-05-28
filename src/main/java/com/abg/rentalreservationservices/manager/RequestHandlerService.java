package com.abg.rentalreservationservices.manager;

import com.abg.rentalreservationservices.entity.Car;
import com.abg.rentalreservationservices.entity.Reservation;
import com.abg.rentalreservationservices.entity.ServicableCity;
import com.abg.rentalreservationservices.requestDTO.BookingUpdationRequest;
import com.abg.rentalreservationservices.responseDTO.AvailableCarsResponse;
import com.abg.rentalreservationservices.service.CarRentalService;
import com.abg.rentalreservationservices.service.CarService;
import com.abg.rentalreservationservices.service.ServicableCityService;
import com.abg.rentalreservationservices.service.UserService;
import exceptions.BadRequestsException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.abg.rentalreservationservices.responseDTO.BookingSuccessResponse;
import com.abg.rentalreservationservices.requestDTO.ReservationRequest;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RequestHandlerService {

    private final ServicableCityService servicableCityService;
    private final CarRentalService carRentalService;
    private final CarService carService;
    private final UserService userService;

    public void prepareHomeView(Model model,Authentication authentication) {
        List<ServicableCity> allServicableCities = servicableCityService.getAllServicableCities();
        String userName = userService.findUserByEmail(authentication.getName()).getName();
        model.addAttribute("userName", userName);
        model.addAttribute("servicableCities", allServicableCities);
    }

    public ResponseEntity<List<AvailableCarsResponse>> getAvailableCars(ReservationRequest reservationRequest) {
        return carRentalService.getAvailableCars(reservationRequest);
    }

    public ResponseEntity<BookingSuccessResponse> reserveCar(Long carId, ReservationRequest reservationRequest, Authentication authentication) throws Exception {
        System.out.println("hello");
        return carRentalService.reserveCar(carId,reservationRequest,authentication);
    }

    public ResponseEntity<BookingSuccessResponse> updateReservation(BookingUpdationRequest bookingUpdationRequest) throws Exception {
        return carRentalService.updateReservation(bookingUpdationRequest);
    }

}
