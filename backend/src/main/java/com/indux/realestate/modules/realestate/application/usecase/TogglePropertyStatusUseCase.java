package com.indux.realestate.modules.realestate.application.usecase;

import com.indux.realestate.modules.realestate.domain.entity.Property;
import com.indux.realestate.modules.realestate.domain.gateway.PropertyGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TogglePropertyStatusUseCase {

    private final PropertyGateway propertyGateway;

    public void execute(String propertyId, String userId) {

        Property property = propertyGateway.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        if (!property.getCreatedByUserId().equals(userId)) {
            throw new RuntimeException("You cannot change this property");
        }

        property.setActive(!property.getActive());

        propertyGateway.save(property);
    }
}