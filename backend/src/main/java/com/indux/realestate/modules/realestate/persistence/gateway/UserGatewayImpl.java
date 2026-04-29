package com.indux.realestate.modules.realestate.persistence.gateway;

import com.indux.realestate.modules.realestate.domain.entity.User;
import com.indux.realestate.modules.realestate.domain.enums.UserRole;
import com.indux.realestate.modules.realestate.domain.gateway.UserGateway;
import com.indux.realestate.modules.realestate.persistence.model.UserModel;
import com.indux.realestate.modules.realestate.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserGatewayImpl implements UserGateway {

    private final UserRepository repository;

    @Override
    public User save(User user) {
        UserModel saved = repository.save(toModel(user));
        return toEntity(saved);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email).map(this::toEntity);
    }

    @Override
    public Optional<User> findById(String id) {
        return repository.findById(id).map(this::toEntity);
    }

    private UserModel toModel(User user) {
        return UserModel.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole().name())
                .build();
    }

    private User toEntity(UserModel model) {
        return User.builder()
                .id(model.getId())
                .name(model.getName())
                .email(model.getEmail())
                .password(model.getPassword())
                .role(UserRole.valueOf(model.getRole()))
                .build();
    }
}