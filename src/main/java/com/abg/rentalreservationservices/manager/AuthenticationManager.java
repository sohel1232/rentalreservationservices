package com.abg.rentalreservationservices.manager;

import com.abg.rentalreservationservices.entity.User;
import com.abg.rentalreservationservices.responseDTO.RegistrationSuccessResponse;
import com.abg.rentalreservationservices.service.AuthenticationService;
import com.abg.rentalreservationservices.service.UserService;
import exceptions.BadRequestsException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import com.abg.rentalreservationservices.requestDTO.RegistrationRequest;

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
    public ResponseEntity<RegistrationSuccessResponse> registerNewUser(@RequestBody RegistrationRequest registrationRequest) {
        User exsistingUser = userService.findUserByEmail(registrationRequest.getEmail());
        if(exsistingUser!=null){
            throw new BadRequestsException("User already exists");
        }

        if(!registrationRequest.getPassword().equals(registrationRequest.getConfirmPassword())){
            throw new BadRequestsException("passwords does not matches");
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

        RegistrationSuccessResponse registrationSuccessResponse=  responseManager.buildSuccessRegistrationResponse(registrationRequest);
        return new ResponseEntity<>(registrationSuccessResponse, HttpStatus.OK);
    }

    @Override
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration-form";
    }

}
