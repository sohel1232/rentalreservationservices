package com.abg.rentalreservationservices.controller;

import com.abg.rentalreservationservices.entity.User;
import com.abg.rentalreservationservices.service.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private AuthenticationService authenticationService;

    public RegistrationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/show-registrationform")
    public String showMyLoginPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration-form";
    }

    @PostMapping("/process-registrationform")
    public String registerNewUser(@ModelAttribute("user") User user){
        return authenticationService.registerNewUser(user);
    }

}
