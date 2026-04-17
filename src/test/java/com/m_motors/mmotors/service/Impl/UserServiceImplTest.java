package com.m_motors.mmotors.service.impl;

import com.m_motors.mmotors.model.Role;
import com.m_motors.mmotors.model.User;
import com.m_motors.mmotors.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        userService = new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Test
    void registerUser_shouldCreateUserSuccessfully() {
        when(userRepository.findByEmail("test@test.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("1234")).thenReturn("encoded1234");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User result = userService.registerUser("John", "Doe", "test@test.com", "1234");

        assertNotNull(result);
        assertEquals("John", result.getPrenom());
        assertEquals("Doe", result.getNom());
        assertEquals("test@test.com", result.getEmail());
        assertEquals("encoded1234", result.getPassword());
        assertEquals(Role.CLIENT, result.getRole());

        verify(userRepository).findByEmail("test@test.com");
        verify(passwordEncoder).encode("1234");
        verify(userRepository).save(any(User.class));
    }

    @Test
    void registerUser_shouldThrowExceptionIfEmailExists() {
        when(userRepository.findByEmail("test@test.com")).thenReturn(Optional.of(new User()));

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                userService.registerUser("John", "Doe", "test@test.com", "1234")
        );

        assertEquals("Un utilisateur avec cet email existe déjà !", exception.getMessage());

        verify(userRepository).findByEmail("test@test.com");
        verify(userRepository, never()).save(any(User.class));
        verify(passwordEncoder, never()).encode(anyString());
    }

    @Test
    void findByEmail_shouldReturnUser() {
        User user = new User();
        user.setEmail("test@test.com");

        when(userRepository.findByEmail("test@test.com")).thenReturn(Optional.of(user));

        Optional<User> result = userService.findByEmail("test@test.com");

        assertTrue(result.isPresent());
        assertEquals("test@test.com", result.get().getEmail());

        verify(userRepository).findByEmail("test@test.com");
    }

    @Test
    void findByEmail_shouldReturnEmptyWhenUserDoesNotExist() {
        when(userRepository.findByEmail("absent@test.com")).thenReturn(Optional.empty());

        Optional<User> result = userService.findByEmail("absent@test.com");

        assertTrue(result.isEmpty());

        verify(userRepository).findByEmail("absent@test.com");
    }

    @Test
    void save_shouldReturnSavedUser() {
        User user = new User();
        user.setEmail("save@test.com");

        when(userRepository.save(user)).thenReturn(user);

        User saved = userService.save(user);

        assertNotNull(saved);
        assertEquals("save@test.com", saved.getEmail());

        verify(userRepository).save(user);
    }
}