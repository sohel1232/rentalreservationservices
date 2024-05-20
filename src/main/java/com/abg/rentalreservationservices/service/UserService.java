package com.abg.rentalreservationservices.service;

import com.abg.rentalreservationservices.entity.User;

public interface UserService {
    User findUserByEmail(String email);

    void save(User user);
}
