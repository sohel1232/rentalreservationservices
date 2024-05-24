package com.abg.rentalreservationservices.manager;

import com.abg.rentalreservationservices.entity.Car;
import com.abg.rentalreservationservices.entity.ServicableCity;
import com.abg.rentalreservationservices.repository.CarRepository;
import com.abg.rentalreservationservices.service.CarService;
import com.abg.rentalreservationservices.service.ServicableCityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.abg.rentalreservationservices.requestDTO.ReservationRequest;

import java.util.List;

@Service
@AllArgsConstructor
public class CarManager implements CarService {

    private final CarRepository carRepository;
    private final ServicableCityService servicableCityService;

    @Override
    public Car getCarById(Long carId) {
        return carRepository.findById(carId).orElse(null);
    }

    @Override
    public List<Car> getAvailableCars(ReservationRequest reservationRequest) {
        ServicableCity sourceCity = servicableCityService.findCityByName(reservationRequest.getSourceCity());

        //List<Car> carsfromrepo = carRepository.findAvailableCars(sourceCity.getName(),reservationRequest.getSeatingCapacity(),reservationRequest.getStartDateTime());
        return carRepository.findAvailableCarsByCityAndCapacity(sourceCity.getName(),reservationRequest.getSeatingCapacity());
    }
}
