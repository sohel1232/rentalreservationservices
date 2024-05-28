package com.abg.rentalreservationservices.manager;

import com.abg.rentalreservationservices.entity.*;
import com.abg.rentalreservationservices.kafka.KafkaReservationSummaryProducer;
import com.abg.rentalreservationservices.requestDTO.BookingUpdationRequest;
import com.abg.rentalreservationservices.responseDTO.AvailableCarsResponse;
import com.abg.rentalreservationservices.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.abg.rentalreservationservices.responseDTO.BookingSuccessResponse;
import com.abg.rentalreservationservices.requestDTO.ReservationRequest;

import java.util.List;

@Service
@AllArgsConstructor
public class CarRentalManager implements CarRentalService {

    private final ReservationService reservationService;
    private CarService carService;
    private final ResponseManager responseManager;
    private final KafkaReservationSummaryProducer kafkaReservationSummaryProducer;

    @Override
    public ResponseEntity<BookingSuccessResponse> reserveCar(Long carId, ReservationRequest reservationRequest, Authentication authentication) throws Exception {
        Reservation reservation = reservationService.makeNewReservation(carId,reservationRequest,authentication);
        ReservationSummary reservationSummary = reservationService.generateReservationSummary(reservation);
        kafkaReservationSummaryProducer.produceReservationSummary(reservationSummary);
        BookingSuccessResponse bookingSuccessResponse =  responseManager.buildSuccessBookingResponse(reservation);
        return new ResponseEntity<>(bookingSuccessResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AvailableCarsResponse>> getAvailableCars(ReservationRequest reservationRequest) {
        List<Car> availableCars = carService.getAvailableCars(reservationRequest);
        List<AvailableCarsResponse> availableCarsResponses = responseManager.buildAvailableCarsResponse(availableCars);
        return new ResponseEntity<>(availableCarsResponses,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookingSuccessResponse> updateReservation(BookingUpdationRequest bookingUpdationRequest) throws Exception {
        Reservation updatedReservation = reservationService.updateReservation(bookingUpdationRequest.getReservationId(),bookingUpdationRequest);
        ReservationSummary reservationSummary = reservationService.generateReservationSummary(updatedReservation);
        kafkaReservationSummaryProducer.produceReservationSummary(reservationSummary);
        BookingSuccessResponse bookingSuccessResponse = responseManager.buildSuccessBookingResponse(updatedReservation);
        return new ResponseEntity<>(bookingSuccessResponse,HttpStatus.OK);
    }
}
