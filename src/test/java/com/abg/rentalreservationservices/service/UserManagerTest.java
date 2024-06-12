package com.abg.rentalreservationservices.service;

import com.abg.rentalreservationservices.entity.User;
import com.abg.rentalreservationservices.manager.UserManager;
import com.abg.rentalreservationservices.repository.UserRepository;
import exceptions.AccessDeniedException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserManagerTest {

    @InjectMocks
    private UserManager userManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Authentication authentication;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindUserByEmail() {
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);
        when(userRepository.findByEmail(email)).thenReturn(user);

        User result = userManager.findUserByEmail(email);

        assertNotNull(result);
        assertEquals(email, result.getEmail());
    }

    @Test
    public void testSaveUser() {
        User user = new User();

        userManager.save(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testGetCurrentLoggedInUser() {
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);
        when(authentication.getName()).thenReturn(email);
        when(userRepository.findByEmail(email)).thenReturn(user);

        User result = userManager.getCurrentLoggedInUser(authentication);

        assertNotNull(result);
        assertEquals(email, result.getEmail());
    }

    @Test
    public void testGetCurrentLoggedInUser_AccessDenied() {
        when(authentication.getName()).thenReturn("unknown@example.com");
        when(userRepository.findByEmail("unknown@example.com")).thenReturn(null);

        assertThrows(AccessDeniedException.class, () -> {
            userManager.getCurrentLoggedInUser(authentication);
        });
    }
}
