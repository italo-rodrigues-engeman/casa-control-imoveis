package com.indux.realestate.modules.realestate.persistence.repository;

import com.indux.realestate.modules.realestate.persistence.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserModel, String> {

    Optional<UserModel> findByEmail(String email);
}