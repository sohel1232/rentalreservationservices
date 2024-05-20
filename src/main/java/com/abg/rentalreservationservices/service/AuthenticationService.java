package com.abg.rentalreservationservices.service;

import com.abg.rentalreservationservices.entity.User;

public interface AuthenticationService {
    String registerNewUser(User user);
}
