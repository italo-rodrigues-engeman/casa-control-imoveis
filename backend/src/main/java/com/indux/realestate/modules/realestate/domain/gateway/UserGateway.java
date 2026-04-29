package com.indux.realestate.modules.realestate.domain.gateway;

import com.indux.realestate.modules.realestate.domain.entity.User;

import java.util.Optional;

public interface UserGateway {

    User save(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(String id);
}