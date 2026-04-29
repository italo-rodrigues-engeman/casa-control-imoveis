package com.indux.realestate.modules.realestate.application.usecase;

import com.indux.realestate.modules.realestate.application.dto.request.CreateUserRequest;
import com.indux.realestate.modules.realestate.domain.entity.User;
import com.indux.realestate.modules.realestate.domain.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {

    private final UserGateway userGateway;
    private final PasswordEncoder passwordEncoder;

    public void execute(CreateUserRequest request) {

        userGateway.findByEmail(request.getEmail())
                .ifPresent(user -> {
                    throw new IllegalStateException("Email already registered");
                });

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userGateway.save(user);
    }
}