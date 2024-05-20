package com.abg.rentalreservationservices.service;

import com.abg.rentalreservation.entity.User;

public interface UserService {
    User findUserByEmail(String email);

    void save(User user);
}
