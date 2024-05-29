package com.abg.rentalreservationservices.manager;

import com.abg.rentalreservationservices.entity.Car;
import com.abg.rentalreservationservices.entity.Reservation;
import com.abg.rentalreservationservices.entity.ServicableCity;
import com.abg.rentalreservationservices.repository.CarRepository;
import com.abg.rentalreservationservices.service.CarService;
import com.abg.rentalreservationservices.service.ServicableCityService;
import exceptions.BadRequestsException;
import exceptions.NotFound;
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
        return carRepository.findById(carId).orElseThrow(() -> new NotFound("car not found with id: " + carId));
    }

    @Override
    public List<Car> getAvailableCars(ReservationRequest reservationRequest) {
        ServicableCity sourceCity = servicableCityService.getServicableCityByName(reservationRequest.getSourceCity());
        List<Car> availbleCars = new ArrayList<>();
        List<Car> eligibleSeatingCapacityCarsFromTheCity = carRepository.findAvailableCarsByCityAndCapacity(sourceCity.getName(), reservationRequest.getSeatingCapacity());

        if(eligibleSeatingCapacityCarsFromTheCity.isEmpty()){
            throw new BadRequestsException("No car available");
        }

        for (Car car : eligibleSeatingCapacityCarsFromTheCity) {
            if (car.getReservations()==null && car.getReservations().isEmpty()) {
                availbleCars.add(car);
            } else {
                if(isReservationPossibleForTheDates(car,reservationRequest.getStartDateTime().toLocalDate(),reservationRequest.getEndDateTime().toLocalDate())){
                    availbleCars.add(car);
                }
            }
        }
        return availbleCars;
    }

    public Boolean isReservationPossibleForTheDates(Car car,LocalDate requestedStartDate, LocalDate requestedEndDate){
        List<Reservation> upcomingReservationsForTheCar = car.getReservations();
        for(Reservation currentReservation : upcomingReservationsForTheCar){

            LocalDate currentReservationStart = currentReservation.getStartDateTime().toLocalDate();
            LocalDate currentReservationEnd = currentReservation.getEndDateTime().toLocalDate();

            if((requestedStartDate.isBefore(currentReservationEnd.plusDays(2))) && (requestedEndDate.isAfter(currentReservationStart.minusDays(2)))){
                return false;
            }
        }
        return true;
    }
}
