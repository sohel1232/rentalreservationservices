package com.abg.rentalreservationservices.controller;

import com.abg.rentalreservationservices.entity.Reservation;
import com.abg.rentalreservationservices.entity.User;
import com.abg.rentalreservationservices.service.CarRentalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/car-rental")
public class HomeController {

    private CarRentalService carRentalService;

    public HomeController(CarRentalService carRentalService) {
        this.carRentalService = carRentalService;
    }

    @GetMapping
    public String showHomePage(Model model){
        return carRentalService.showRentingForm(model);
    }

    @PostMapping("/process-reservation")
    public String processReservation(@ModelAttribute("reservation") Reservation reservation){
        return carRentalService.processReservation(reservation);
    }

}
