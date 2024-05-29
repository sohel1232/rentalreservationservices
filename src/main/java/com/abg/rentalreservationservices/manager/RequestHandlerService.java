package com.abg.rentalreservationservices.manager;

import com.abg.rentalreservationservices.entity.Reservation;
import com.abg.rentalreservationservices.entity.ServicableCity;
import com.abg.rentalreservationservices.entity.User;
import com.abg.rentalreservationservices.requestDTO.BookingUpdationRequest;
import com.abg.rentalreservationservices.responseDTO.AvailableCarsResponse;
import com.abg.rentalreservationservices.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.abg.rentalreservationservices.responseDTO.BookingSuccessResponse;
import com.abg.rentalreservationservices.requestDTO.ReservationRequest;

import java.util.List;

@Service
@AllArgsConstructor
public class RequestHandlerService {

    private final ServicableCityService servicableCityService;
    private final CarRentalService carRentalService;
    private final CarService carService;
    private final UserService userService;
    private final ReservationService reservationService;

    public void prepareNewReservationFormView(Model model, Authentication authentication) {
        List<ServicableCity> allServicableCities = servicableCityService.getAllServicableCities();
        User currentUser =  userService.findUserByEmail(authentication.getName());
        String userName = currentUser.getName();
        String phoneNumber = currentUser.getPhoneNumber();
        model.addAttribute("userName", userName);
        model.addAttribute("phoneNumber", phoneNumber);
        model.addAttribute("servicableCities", allServicableCities);
    }

    public ResponseEntity<List<AvailableCarsResponse>> getAvailableCars(ReservationRequest reservationRequest) {
        return carRentalService.getAvailableCars(reservationRequest);
    }

    public ResponseEntity<BookingSuccessResponse> reserveCar(Long carId, ReservationRequest reservationRequest, Authentication authentication) throws Exception {
        return carRentalService.reserveCar(carId,reservationRequest,authentication);
    }

    public ResponseEntity<BookingSuccessResponse> updateReservation(BookingUpdationRequest bookingUpdationRequest) throws Exception {
        return carRentalService.updateReservation(bookingUpdationRequest);
    }

    public void prepareReservationSummaryView(Model model, String reservationId) {
        Reservation reservation = reservationService.getReservationById(Long.parseLong(reservationId));
        model.addAttribute("reservation",reservation);
    }

    public void prepareHomeView(Authentication authentication, Model model) {
        String userName = userService.findUserByEmail(authentication.getName()).getName();
        model.addAttribute("userName",userName);
    }

    public void prepareMyReservationsView(Model model, Authentication authentication) {
        User currentUser = userService.getCurrentLoggedInUser(authentication);
        String userName = currentUser.getName();
        List<Reservation> userReservations = currentUser.getReservations();
        model.addAttribute("userReservations",userReservations);
        model.addAttribute("userName",userName);
    }

    public void prepareReservationsUpdationView(String reservationId, Model model) {
        Reservation reservation = reservationService.getReservationById(Long.parseLong(reservationId));
        model.addAttribute("reservationId",reservation.getId());
        model.addAttribute("reserverName",reservation.getReserverName());
        model.addAttribute("phoneNumber",reservation.getReserverPhoneNumber());
        model.addAttribute("pickUpAddress",reservation.getPickUpAddress());
        model.addAttribute("dropOffAddress",reservation.getDropOffAddress());
        model.addAttribute("startDate",reservation.getStartDateTime().toLocalDate());
        model.addAttribute("endDate",reservation.getEndDateTime().toLocalDate());
        model.addAttribute("startTime",reservation.getStartDateTime().toLocalTime());
        model.addAttribute("endTime",reservation.getEndDateTime().toLocalTime());
    }
}
