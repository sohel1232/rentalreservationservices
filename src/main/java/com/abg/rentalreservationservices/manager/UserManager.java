package com.abg.rentalreservationservices.manager;

import com.abg.rentalreservationservices.entity.User;
import com.abg.rentalreservationservices.repository.UserRepository;
import com.abg.rentalreservationservices.service.UserService;
import exceptions.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserManager implements UserService {

    private UserRepository userRepository;

    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User getCurrentLoggedInUser(Authentication authentication) {
       User user =  userRepository.findByEmail(authentication.getName());
        if(user==null){
            throw new AccessDeniedException("User not authenticated");
        }
        return user;
    }

}
