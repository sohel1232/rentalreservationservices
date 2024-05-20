package com.abg.rentalreservationservices.manager;

import com.abg.rentalreservation.entity.User;
import com.abg.rentalreservation.service.AuthenticationService;
import com.abg.rentalreservation.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationManager implements AuthenticationService {

    private final UserService userService;

    public AuthenticationManager(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String registerNewUser(User user) {
        User exsistingUser = userService.findUserByEmail(user.getEmail());
        if(exsistingUser!=null){
            throw new RuntimeException("User aready exsists");
        }

        userService.save(user);
        return "registration-confirmation";
    }

}
