package com.abg.rentalreservationservices.manager;

import com.abg.rentalreservationservices.entity.Car;
import com.abg.rentalreservationservices.entity.Reservation;
import com.abg.rentalreservationservices.entity.ServicableCity;
import com.abg.rentalreservationservices.entity.User;
import com.abg.rentalreservationservices.repository.CarRepository;
import com.abg.rentalreservationservices.repository.ReservationRepository;
import com.abg.rentalreservationservices.responseDTO.AvailableCarsResponse;
import com.abg.rentalreservationservices.service.CarRentalService;
import com.abg.rentalreservationservices.service.ServicableCityService;
import com.abg.rentalreservationservices.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import requestDTO.BookingSuccessResponse;
import requestDTO.ReservationRequest;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RequestHandlerService {

    private final ServicableCityService servicableCityService;
    private final CarRentalService carRentalService;

    public void prepareHomeView(Model model) {
        List<ServicableCity> allServicableCities = servicableCityService.getAllServicableCities();
//        model.addAttribute("reservation", new Reservation());
        model.addAttribute("servicableCities", allServicableCities);
    }

    public List<AvailableCarsResponse> getAvailableCars(ReservationRequest reservationRequest) {
        return carRentalService.getAvailableCars(reservationRequest);
    }

    public BookingSuccessResponse reserveCar(Long carId, ReservationRequest reservationRequest,Authentication authentication) {
        return carRentalService.reserveCar(carId,reservationRequest,authentication);
    }
}