package com.abg.rentalreservationservices.restcontroller;

import com.abg.rentalreservationservices.manager.RequestHandlerService;
import com.abg.rentalreservationservices.requestDTO.BookingUpdationRequest;
import com.abg.rentalreservationservices.responseDTO.AvailableCarsResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.abg.rentalreservationservices.responseDTO.BookingSuccessResponse;
import com.abg.rentalreservationservices.requestDTO.ReservationRequest;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
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
    public BookingSuccessResponse bookRentalCar(@PathVariable Long carId, Authentication authentication) throws Exception {
        ReservationRequest reservationRequest = this.currentReservationRequest;
        return requestHandlerService.reserveCar(carId, reservationRequest,authentication);
    }

    @PutMapping("/update/{reservationId}")
    public BookingSuccessResponse updateReservation(@PathVariable Long reservationId,
                                                            @RequestBody BookingUpdationRequest bookingUpdationRequest) throws Exception {
        return requestHandlerService.updateReservation(reservationId,bookingUpdationRequest);
    }
}
