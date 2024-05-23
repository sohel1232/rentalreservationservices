package com.abg.rentalreservationservices.manager;

import com.abg.rentalreservationservices.entity.*;
import com.abg.rentalreservationservices.kafka.KafkaReservationSummaryProducer;
import com.abg.rentalreservationservices.responseDTO.AvailableCarsResponse;
import com.abg.rentalreservationservices.service.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.abg.rentalreservationservices.responseDTO.BookingSuccessResponse;
import requestDTO.ReservationRequest;

import java.util.List;

@Service
@AllArgsConstructor
public class CarRentalManager implements CarRentalService {

    private final ReservationService reservationService;
    private CarService carService;
    private final ResponseManager responseManager;
    private final KafkaReservationSummaryProducer kafkaReservationSummaryProducer;

    @Override
    public BookingSuccessResponse reserveCar(Long carId, ReservationRequest reservationRequest, Authentication authentication) {
        Reservation reservation = reservationService.makeNewReservation(carId,reservationRequest,authentication);
        ReservationSummary reservationSummary = reservationService.generateReservationSummary(reservation);
        kafkaReservationSummaryProducer.produceReservationSummary(reservationSummary);
        return responseManager.buildSuccessBookingResponse(reservation);
    }

    @Override
    public List<AvailableCarsResponse> getAvailableCars(ReservationRequest reservationRequest) {
        List<Car> availableCars = carService.getAvailableCars(reservationRequest);
        return responseManager.buildAvailableCarsResponse(availableCars);
    }
}
