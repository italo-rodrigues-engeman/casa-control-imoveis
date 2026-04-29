package com.indux.realestate.modules.realestate.domain.gateway;

import com.indux.realestate.modules.realestate.domain.entity.Property;

import java.util.List;
import java.util.Optional;

public interface PropertyGateway {

    Property save(Property property);

    Optional<Property> findById(String id);

    List<Property> findAll();

    void deactivate(String id);


}