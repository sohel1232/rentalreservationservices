package com.abg.rentalreservationservices.restcontroller;

import com.abg.rentalreservationservices.manager.AuthenticationManager;
import com.abg.rentalreservationservices.responseDTO.RegistrationSuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.abg.rentalreservationservices.requestDTO.RegistrationRequest;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<RegistrationSuccessResponse> registerNewUser(@RequestBody RegistrationRequest registrationRequest){
        return authenticationManager.registerNewUser(registrationRequest);
    }
}
