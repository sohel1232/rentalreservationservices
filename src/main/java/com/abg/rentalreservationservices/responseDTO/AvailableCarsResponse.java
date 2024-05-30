package com.abg.rentalreservationservices.responseDTO;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AvailableCarsResponse {
    private Long id;

    private String name;

    private String plate;

    private String type;

    private Integer seatingCapacity;

    private BigDecimal basePrice;

    private String carImage;
}
