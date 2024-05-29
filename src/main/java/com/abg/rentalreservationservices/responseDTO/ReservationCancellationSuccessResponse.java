package com.abg.rentalreservationservices.responseDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReservationCancellationSuccessResponse {
    private String message;
}
