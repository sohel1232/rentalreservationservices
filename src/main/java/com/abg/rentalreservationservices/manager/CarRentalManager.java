package com.abg.rentalreservationservices.manager;

import com.abg.rentalreservationservices.entity.Reservation;
import com.abg.rentalreservationservices.entity.ServicableCity;
import com.abg.rentalreservationservices.service.CarRentalService;
import com.abg.rentalreservationservices.service.ServicableCityService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class CarRentalManager implements CarRentalService {

    private ServicableCityService servicableCityService;

    public CarRentalManager(ServicableCityService servicableCityService) {
        this.servicableCityService = servicableCityService;
    }

    @Override
    public String showRentingForm(Model model) {
        Reservation reservation = new Reservation();
        List<ServicableCity> allServicableCities = servicableCityService.getAllServicableCities();
        model.addAttribute("reservation", reservation);
        model.addAttribute("servicableCities", allServicableCities);
        return "home";
    }

    @Override
    public String processReservation(Reservation reservation) {
        return "home";
    }
}
