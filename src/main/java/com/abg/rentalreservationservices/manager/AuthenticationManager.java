package com.abg.rentalreservationservices.manager;

import com.abg.rentalreservationservices.entity.User;
import com.abg.rentalreservationservices.service.AuthenticationService;
import com.abg.rentalreservationservices.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userService.save(user);

        return "registration-confirmation";
    }

    @Override
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration-form";
    }

}
