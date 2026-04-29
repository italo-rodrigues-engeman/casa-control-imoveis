package com.indux.realestate.modules.realestate.persistence.repository;

import com.indux.realestate.modules.realestate.persistence.model.FavoriteModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FavoriteRepository extends MongoRepository<FavoriteModel, String> {

    List<FavoriteModel> findByUserId(String userId);
}