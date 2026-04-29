package com.indux.realestate.modules.realestate.persistence.repository;

import com.indux.realestate.modules.realestate.persistence.model.PropertyModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PropertyRepository extends MongoRepository<PropertyModel, String> {
}