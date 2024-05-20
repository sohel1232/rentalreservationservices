package com.abg.rentalreservationservices.manager;

import com.abg.rentalreservation.entity.User;
import com.abg.rentalreservation.repository.UserRepository;
import com.abg.rentalreservation.service.UserService;
import org.springframework.stereotype.Service;

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

}
