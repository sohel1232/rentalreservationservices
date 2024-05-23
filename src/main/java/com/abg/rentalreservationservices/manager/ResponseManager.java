package com.abg.rentalreservationservices.manager;

import com.abg.rentalreservationservices.entity.Car;
import com.abg.rentalreservationservices.entity.Reservation;
import com.abg.rentalreservationservices.responseDTO.AvailableCarsResponse;
import org.springframework.stereotype.Service;
import requestDTO.BookingSuccessResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResponseManager {
    public BookingSuccessResponse buildSuccessBookingResponse(Reservation reservation) {
        return BookingSuccessResponse.builder()
                .carName(reservation.getCar().getName())
                .carPlate(reservation.getCar().getPlate())
                .seatingCapacity(reservation.getCar().getSeatingCapacity())
                .reserverName(reservation.getUser().getName())
                .reserverPhoneNumber(reservation.getUser().getPhoneNumber())
                .reserverEmail(reservation.getUser().getEmail())
                .sourceCity(reservation.getSourceCity().getName())
                .destinationCity(reservation.getDestinationCity().getName())
                .pickUpAddress(reservation.getPickUpAddress())
                .dropOffAddress(reservation.getDropOffAddress())
                .startDateTime(reservation.getStartDateTime())
                .endDateTime(reservation.getEndDateTime())
                .amountRecieved(reservation.getAmountRecieved())
                .build();
    }

    public List<AvailableCarsResponse> buildAvailableCarsResponse(List<Car> availableCars) {
        List<AvailableCarsResponse> availableCarsResponses = new ArrayList<>();
        for(Car car : availableCars){
            AvailableCarsResponse availableCarsResponse = AvailableCarsResponse.builder()
                    .id(car.getId())
                    .plate(car.getPlate())
                    .type(car.getType())
                    .name(car.getName())
                    .basePrice(car.getBasePrice())
                    .seatingCapacity(car.getSeatingCapacity())
                    .build();
            availableCarsResponses.add(availableCarsResponse);
        }
        return availableCarsResponses;
    }
}
