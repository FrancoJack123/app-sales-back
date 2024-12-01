package com.example.appsalesback.service.implementation;

import com.example.appsalesback.persistence.repository.UserRepository;
import com.example.appsalesback.presentation.dto.UserDto;
import com.example.appsalesback.service.interfaces.UserService;
import com.example.appsalesback.util.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto getUserByEmail(String email) {
        return userMapper.toDto(userRepository.findByEmail(email).orElseThrow());
    }
}
