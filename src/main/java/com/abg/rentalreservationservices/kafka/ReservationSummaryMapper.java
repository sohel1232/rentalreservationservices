package com.abg.rentalreservationservices.kafka;

import com.abg.rentalreservationservices.avro.ReservationSummaryAvro;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Component;

@Component
public class ReservationSummaryMapper {

    public static ReservationSummaryAvro mapToAvro(com.abg.rentalreservationservices.entity.ReservationSummary model) {
        return ReservationSummaryAvro.newBuilder()
                .setReserverName(model.getReserverName())
                .setReserverEmail(model.getReserverEmail())
                .setReserverPhoneNumber(model.getReserverPhoneNumber())
                .setReserverDrivingLicenceNumber(model.getReserverDrivingLicenceNumber())
                .setCarName(model.getCarName())
                .setCarPlate(model.getCarPlate())
                .setCarType(model.getCarType())
                .setCarSeatingCapacity(model.getCarSeatingCapacity())
                .setSourceCity(model.getSourceCity())
                .setDestinationCity(model.getDestinationCity())
                .setPickUpAddress(model.getPickUpAddress())
                .setDropOffAddress(model.getDropOffAddress())
                .setStartDateTime(model.getStartDateTime())
                .setEndDateTime(model.getEndDateTime())
                .setReservationAmount(model.getReservationAmount().toString())
                .build();
    }

}
