package com.abg.rentalreservationservices.service;

import com.abg.rentalreservationservices.entity.Car;
import com.abg.rentalreservationservices.requestDTO.ReservationRequest;

import java.util.List;

public interface CarService {
    Car getCarById(Long carId);

    List<Car> getAvailableCars(ReservationRequest reservationRequest);
}
