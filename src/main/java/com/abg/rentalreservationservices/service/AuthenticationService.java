package com.abg.rentalreservationservices.service;

import com.abg.rentalreservationservices.entity.User;
import org.springframework.ui.Model;

public interface AuthenticationService {
    String registerNewUser(User user);

    String showRegistrationForm(Model model);
}
