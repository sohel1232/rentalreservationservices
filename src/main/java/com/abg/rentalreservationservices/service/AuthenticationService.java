package com.abg.rentalreservationservices.service;

import com.abg.rentalreservationservices.entity.User;
import com.abg.rentalreservationservices.responseDTO.RegistrationSuccessResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import com.abg.rentalreservationservices.requestDTO.RegistrationRequest;

public interface AuthenticationService {
    String registerNewUser(User user);

    RegistrationSuccessResponse registerNewUser(@RequestBody RegistrationRequest registrationRequest);

    String showRegistrationForm(Model model);
}
