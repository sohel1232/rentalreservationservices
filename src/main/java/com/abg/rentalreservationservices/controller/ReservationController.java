package com.abg.rentalreservationservices.controller;

import com.abg.rentalreservationservices.entity.Car;
import com.abg.rentalreservationservices.entity.Reservation;
import com.abg.rentalreservationservices.manager.RequestHandlerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/car-rental")
@AllArgsConstructor
public class ReservationController {

    private final RequestHandlerService requestHandlerService;
    private final String VIEW_HOMEPAGE = "home";

    @GetMapping
    public String showHomePage(Model model){
//        requestHandlerService.prepareHomeView(model);
        return "home2";
    }

//    @PostMapping("/search-cars")
//    @ResponseBody
//    public List<Car> showAvailableCars(@RequestBody Reservation reservation) {
//        return  reservation.getSourceCity().getCars();
//    }

//    @PostMapping("/search-cars")
//    public String showAvailableCars(Model model,@ModelAttribute Reservation reservation) {
//        requestHandlerService.processAvailableCarsRequest(model,reservation);
//        return VIEW_HOMEPAGE;
//    }

}
