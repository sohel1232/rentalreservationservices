package com.abg.rentalreservationservices.restcontroller;

import com.abg.rentalreservationservices.manager.RequestHandlerService;
import com.abg.rentalreservationservices.responseDTO.AvailableCarsResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import requestDTO.BookingSuccessResponse;
import requestDTO.ReservationRequest;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReservationRestController {

    private final RequestHandlerService requestHandlerService;
    private ReservationRequest currentReservationRequest;

    public ReservationRestController(RequestHandlerService requestHandlerService) {
        this.requestHandlerService = requestHandlerService;
    }

    @GetMapping("/cars")
    public List<AvailableCarsResponse> getAvailableCars(@RequestBody ReservationRequest reservationRequest){
        this.currentReservationRequest = reservationRequest;
        return requestHandlerService.getAvailableCars(reservationRequest);
    }

    @PostMapping("/book/{carId}")
    public BookingSuccessResponse bookRentalCar(@PathVariable Long carId, Authentication authentication) {
        ReservationRequest reservationRequest = this.currentReservationRequest;
        return requestHandlerService.reserveCar(carId, reservationRequest,authentication);
    }
}
