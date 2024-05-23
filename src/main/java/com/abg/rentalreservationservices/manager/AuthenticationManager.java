package com.abg.rentalreservationservices.manager;

import com.abg.rentalreservationservices.entity.User;
import com.abg.rentalreservationservices.responseDTO.RegistrationSuccessResponse;
import com.abg.rentalreservationservices.service.AuthenticationService;
import com.abg.rentalreservationservices.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import requestDTO.RegistrationRequest;

@Service
@AllArgsConstructor
public class AuthenticationManager implements AuthenticationService {

    private final UserService userService;
    private final ResponseManager responseManager;

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
    public RegistrationSuccessResponse registerNewUser(@RequestBody RegistrationRequest registrationRequest) {
        User exsistingUser = userService.findUserByEmail(registrationRequest.getEmail());
        if(exsistingUser!=null){
            System.out.println("User already exsists");//returning exception later on
        }

        if(!registrationRequest.getPassword().equals(registrationRequest.getConfirmPassword())){
            System.out.println("Password not matching");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(registrationRequest.getPassword());

        userService.save(
                User.builder()
                        .name(registrationRequest.getName())
                        .email(registrationRequest.getEmail())
                        .phoneNumber(registrationRequest.getPhoneNumber())
                        .drivingLicenceNumber(registrationRequest.getDrivingLicenceNumber())
                        .password(encodedPassword)
                        .build()
        );

        return responseManager.buildSuccessRegistrationResponse(registrationRequest);
    }

    @Override
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration-form";
    }

}
