package com.abg.rentalreservationservices.service;

import com.abg.rentalreservationservices.entity.User;
import org.springframework.security.core.Authentication;

public interface UserService {
    User findUserByEmail(String email);

    void save(User user);

    User getCurrentLoggedInUser(Authentication authentication);
}
