package com.abg.rentalreservationservices.responseDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationSuccessResponse {
    private String email;
    private String message;
}
