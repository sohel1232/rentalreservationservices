package com.abg.rentalreservationservices.restcontroller;

import com.abg.rentalreservationservices.manager.RequestHandlerService;
import com.abg.rentalreservationservices.requestDTO.BookingUpdationRequest;
import com.abg.rentalreservationservices.responseDTO.AvailableCarsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.abg.rentalreservationservices.responseDTO.BookingSuccessResponse;
import com.abg.rentalreservationservices.requestDTO.ReservationRequest;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReservationRestController {

    private final RequestHandlerService requestHandlerService;
    private ReservationRequest currentReservationRequest;

    public ReservationRestController(RequestHandlerService requestHandlerService) {
        this.requestHandlerService = requestHandlerService;
    }

    @PostMapping("/cars")
    public ResponseEntity<List<AvailableCarsResponse>> getAvailableCars(@RequestBody ReservationRequest reservationRequest){
        this.currentReservationRequest = reservationRequest;
        System.out.println("HELLO HELLO " + reservationRequest);
        return requestHandlerService.getAvailableCars(reservationRequest);
    }

    @PostMapping("/book/{carId}")
    public ResponseEntity<BookingSuccessResponse> bookRentalCar(@PathVariable Long carId, Authentication authentication) throws Exception {
        ReservationRequest reservationRequest = this.currentReservationRequest;
        System.out.println("HII " + reservationRequest);
        return requestHandlerService.reserveCar(carId, reservationRequest,authentication);
    }

    @PutMapping("/update-reservation")
    public ResponseEntity<BookingSuccessResponse> updateReservation(@RequestBody BookingUpdationRequest bookingUpdationRequest) throws Exception {
        return requestHandlerService.updateReservation(bookingUpdationRequest);
    }

}
