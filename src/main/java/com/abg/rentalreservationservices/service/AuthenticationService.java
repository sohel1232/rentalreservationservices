package com.abg.rentalreservationservices.service;

import com.abg.rentalreservation.entity.User;

public interface AuthenticationService {
    String registerNewUser(User user);
}
