package com.example.appsalesback.service.interfaces;

import com.example.appsalesback.presentation.dto.UserDto;

public interface UserService {
    UserDto getUserByEmail(String email);
}
