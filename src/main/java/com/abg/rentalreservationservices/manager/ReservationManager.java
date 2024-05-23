package com.abg.rentalreservationservices.manager;

import com.abg.rentalreservationservices.entity.Car;
import com.abg.rentalreservationservices.entity.Reservation;
import com.abg.rentalreservationservices.entity.ReservationSummary;
import com.abg.rentalreservationservices.entity.User;
import com.abg.rentalreservationservices.repository.ReservationRepository;
import com.abg.rentalreservationservices.service.CarService;
import com.abg.rentalreservationservices.service.ReservationService;
import com.abg.rentalreservationservices.service.ServicableCityService;
import com.abg.rentalreservationservices.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import requestDTO.ReservationRequest;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class ReservationManager implements ReservationService {

    private final CarService carService;
    private final UserService userService;
    private final ServicableCityService servicableCityService;
    private final ReservationRepository reservationRepository;

    @Override
    public Reservation makeNewReservation(Long carId, ReservationRequest reservationRequest, Authentication authentication) {
        Car car = carService.getCarById(carId);
        User currentLoggedInUser = userService.getCurrentLoggedInUser(authentication);

        return reservationRepository.save(
                Reservation.builder()
                        .sourceCity(servicableCityService.findCityByName(reservationRequest.getSourceCity()))
                        .destinationCity(servicableCityService.findCityByName(reservationRequest.getDestinationCity()))
                        .car(car)
                        .user(currentLoggedInUser)
                        .pickUpAddress(reservationRequest.getPickUpAddress())
                        .dropOffAddress(reservationRequest.getDropOffAddress())
                        .seatingCapacity(reservationRequest.getSeatingCapacity())
                        .startDateTime(reservationRequest.getStartDateTime())
                        .endDateTime(reservationRequest.getEndDateTime())
                        .build()
        );
    }

    @Override
    public ReservationSummary generateReservationSummary(Reservation reservation) {
        return ReservationSummary.builder()
                .carName(reservation.getCar().getName())
                .carPlate(reservation.getCar().getPlate())
                .carType(reservation.getCar().getType())
                .carSeatingCapacity(reservation.getCar().getSeatingCapacity())
                .reserverName(reservation.getUser().getName())
                .reserverEmail(reservation.getUser().getEmail())
                .reserverPhoneNumber(reservation.getUser().getPhoneNumber())
                .reserverDrivingLicenceNumber(reservation.getUser().getDrivingLicenceNumber())
                .sourceCity(reservation.getSourceCity().getName())
                .destinationCity(reservation.getDestinationCity().getName())
                .pickUpAddress(reservation.getPickUpAddress())
                .dropOffAddress(reservation.getDropOffAddress())
                .startDateTime(String.valueOf(reservation.getStartDateTime()))
                .endDateTime(String.valueOf(reservation.getEndDateTime()))
                .reservationAmount(reservation.getAmountRecieved())
                .build();
    }
}
