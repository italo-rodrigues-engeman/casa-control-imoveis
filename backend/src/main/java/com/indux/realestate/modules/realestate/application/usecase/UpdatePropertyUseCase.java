package com.indux.realestate.modules.realestate.application.usecase;

import com.indux.realestate.modules.realestate.application.dto.request.UpdatePropertyRequest;
import com.indux.realestate.modules.realestate.domain.entity.Property;
import com.indux.realestate.modules.realestate.domain.gateway.PropertyGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePropertyUseCase {

    private final PropertyGateway propertyGateway;

    public void execute(String propertyId, UpdatePropertyRequest request, String userId) {

        Property property = propertyGateway.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        if (!property.getCreatedByUserId().equals(userId)) {
            throw new RuntimeException("You cannot edit this property");
        }

        property.setTitle(request.getTitle());
        property.setDescription(request.getDescription());
        property.setPrice(request.getPrice());
        property.setRooms(request.getRooms());
        property.setType(request.getType());

        propertyGateway.save(property);
    }
}