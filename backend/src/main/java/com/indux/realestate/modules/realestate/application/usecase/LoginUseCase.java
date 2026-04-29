package com.indux.realestate.modules.realestate.application.usecase;

import com.indux.realestate.modules.realestate.application.dto.request.LoginRequest;
import com.indux.realestate.modules.realestate.application.dto.response.LoginResponse;
import com.indux.realestate.modules.realestate.domain.entity.User;
import com.indux.realestate.modules.realestate.domain.gateway.UserGateway;
import com.indux.realestate.modules.realestate.infra.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUseCase {

    private final UserGateway userGateway;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginResponse execute(LoginRequest request) {

        User user = userGateway.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        boolean passwordMatches = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!passwordMatches) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        String token = jwtService.generateToken(user);

        return LoginResponse.builder()
                .token(token)
                .role(user.getRole().name())
                .build();
    }
}