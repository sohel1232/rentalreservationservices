package com.abg.rentalreservationservices.controller;

import com.abg.rentalreservationservices.manager.RequestHandlerService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/car-rental")
@AllArgsConstructor
public class ReservationController {

    private final RequestHandlerService requestHandlerService;
    private final String VIEW_HOMEPAGE = "home";
    private final String NEWRESERVATION = "new-reservation-form";
    private final String RESERVATION_SUCCESS = "reservation-success-page";
    private final String VIEW_MYRESERVATIONS = "my-reservations";
    private final String VIEW_RESERVATION_UPDATION = "update-my-reservations";

    @GetMapping
    public String showHomePage(Authentication authentication,Model model){
        requestHandlerService.prepareHomeView(authentication,model);
        return VIEW_HOMEPAGE;
    }

    @GetMapping("/new-reservation")
    public String showNewReservationForm(Model model, Authentication authentication){
        requestHandlerService.prepareNewReservationFormView(model,authentication);
        return NEWRESERVATION;
    }


    @GetMapping("/reservation-success")
    public String showReservationSummary(Model model, @RequestParam(name = "reservationId") String reservationId) {
        requestHandlerService.prepareReservationSummaryView(model,reservationId);
        return RESERVATION_SUCCESS;
    }

    @GetMapping("/my-reservations")
    public String showUserReservations(Model model,Authentication authentication) {
        requestHandlerService.prepareMyReservationsView(model,authentication);
        return VIEW_MYRESERVATIONS;
    }

    @PostMapping("/update-reservation")
    public String updateReservationForm(@RequestParam String reservationId,Model model) {
        requestHandlerService.prepareReservationsUpdationView(reservationId,model);
        return VIEW_RESERVATION_UPDATION;
    }
}
