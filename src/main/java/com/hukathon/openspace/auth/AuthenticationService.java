package com.hukathon.openspace.auth;

import com.hukathon.openspace.config.JwtService;
import com.hukathon.openspace.user.User;
import com.hukathon.openspace.user.UserRepository;
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
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println(-1);
        System.out.println(request.getEmail() +  request.getPassword());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        "ducanh@gmail.com",
                        "1235"
                )
        );
        System.out.println(0);
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        System.out.println(1);
        var jwtToken = jwtService.generateToken(user);
        System.out.println(2);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
