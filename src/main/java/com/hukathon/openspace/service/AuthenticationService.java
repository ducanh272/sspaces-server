package com.hukathon.openspace.service;

import com.hukathon.openspace.auth.AuthenticationRequest;
import com.hukathon.openspace.auth.AuthenticationResponse;
import com.hukathon.openspace.auth.RegisterRequest;
import com.hukathon.openspace.config.JwtService;
import com.hukathon.openspace.dto.CustomUserDetails;
import com.hukathon.openspace.entity.User;
import com.hukathon.openspace.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .address(request.getAddress())
                .phone(request.getPhone())
                .gender(request.getGender())
                .build();
        repository.save(user);

        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        var jwtToken = jwtService.generateToken(customUserDetails);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        var jwtToken = jwtService.generateToken(customUserDetails);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(user)
                .build();
    }
}
