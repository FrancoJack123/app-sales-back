package com.example.appsalesback.service.interfaces;

import com.example.appsalesback.presentation.dto.UserDto;
import com.example.appsalesback.presentation.response.AuthResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AuthResponse createUser(UserDto userDto);
    AuthResponse loginUser(String email, String password);
    Authentication authenticate(String email, String password);
}
