package com.example.appsalesback.service.implementation;

import com.example.appsalesback.configuration.security.jwt.JwtProvider;
import com.example.appsalesback.persistence.entity.Role;
import com.example.appsalesback.persistence.entity.User;
import com.example.appsalesback.persistence.enums.RoleName;
import com.example.appsalesback.persistence.repository.RoleRepository;
import com.example.appsalesback.persistence.repository.UserRepository;
import com.example.appsalesback.presentation.dto.UserDto;
import com.example.appsalesback.presentation.response.AuthResponse;
import com.example.appsalesback.service.interfaces.UserService;
import com.example.appsalesback.util.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public AuthResponse createUser(UserDto userDto) {
        Set<Role> roles = userDto.rolesNames().stream()
                .map(roleName -> roleRepository.findByRoleName(RoleName.valueOf(roleName))
                        .orElseThrow(() -> new IllegalArgumentException("Invalid role: " + roleName)))
                .collect(Collectors.toSet());

        User user = userMapper.toEntity(userDto);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(userDto.password()));

        user = userRepository.save(user);

        Set<SimpleGrantedAuthority> grantedAuthorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
                .collect(Collectors.toSet());
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(user, null, grantedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtProvider.createToken(authentication);
        return new AuthResponse(userDto.email(), accessToken, true);
    }

    @Override
    public AuthResponse loginUser(String email, String password) {
        Authentication authentication = this.authenticate(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtProvider.createToken(authentication);
        return new AuthResponse(email, accessToken, true);
    }

    @Override
    public Authentication authenticate(String email, String password) {
        UserDetails userDetails = loadUserByUsername(email);
        if (userDetails == null)
            throw new BadCredentialsException("Invalid username or password");
        if (!passwordEncoder.matches(password, userDetails.getPassword()))
            throw new BadCredentialsException("Incorrect Password");
        return new UsernamePasswordAuthenticationToken(email, password, userDetails.getAuthorities());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        Set<SimpleGrantedAuthority> grantedAuthorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
                .collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                user.isAccountNonExpired(),
                user.isCredentialsNonExpired(),
                user.isAccountNonLocked(),
                grantedAuthorities
        );
    }
}
