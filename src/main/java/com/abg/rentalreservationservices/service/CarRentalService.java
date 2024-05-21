package com.abg.rentalreservationservices.service;

import com.abg.rentalreservationservices.entity.Reservation;
import org.springframework.ui.Model;

public interface CarRentalService {
    String showRentingForm(Model model);

    String processReservation(Reservation reservation);
}
