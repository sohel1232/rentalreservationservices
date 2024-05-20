package com.abg.rentalreservationservices.manager;

import com.abg.rentalreservationservices.entity.User;
import com.abg.rentalreservationservices.service.AuthenticationService;
import com.abg.rentalreservationservices.service.UserService;
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
