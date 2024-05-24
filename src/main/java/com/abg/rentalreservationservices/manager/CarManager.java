package com.abg.rentalreservationservices.manager;

import com.abg.rentalreservationservices.entity.Car;
import com.abg.rentalreservationservices.entity.ServicableCity;
import com.abg.rentalreservationservices.repository.CarRepository;
import com.abg.rentalreservationservices.service.CarService;
import com.abg.rentalreservationservices.service.ServicableCityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.abg.rentalreservationservices.requestDTO.ReservationRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        LocalDate startDate = reservationRequest.getStartDateTime().toLocalDate();
        LocalDate endDate = reservationRequest.getEndDateTime().toLocalDate();

        List<Car> availbleCars = new ArrayList<>();
        List<Car> eligibleSeatingCapacityCarsFromTheCity = carRepository.findAvailableCarsByCityAndCapacity(sourceCity.getName(), reservationRequest.getSeatingCapacity());

        for (Car car : eligibleSeatingCapacityCarsFromTheCity) {
            if (car.getReservation() == null) {
                availbleCars.add(car);
            } else {
                LocalDate alreadyReservationStartDate = car.getReservation().getStartDateTime().toLocalDate();
                LocalDate alreadyReservationEndDate = car.getReservation().getEndDateTime().toLocalDate();

                boolean noConflict = endDate.isBefore(alreadyReservationStartDate.minusDays(1)) || startDate.isAfter(alreadyReservationEndDate.plusDays(1));
                if (noConflict) {
                    System.out.println("Car is availble to for reservation");
                    availbleCars.add(car);
                }
            }
        }
        return availbleCars;
    }
}
