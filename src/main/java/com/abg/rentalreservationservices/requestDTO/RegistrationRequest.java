package com.abg.rentalreservationservices.requestDTO;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String name;
    private String email;
    private String phoneNumber;
    private String drivingLicenceNumber;
    private String password;
    private String confirmPassword;
}
