package com.indux.realestate.modules.realestate.application.usecase;

import com.indux.realestate.modules.realestate.application.dto.response.PropertyResponseDTO;
import com.indux.realestate.modules.realestate.domain.entity.Property;
import com.indux.realestate.modules.realestate.domain.gateway.PropertyGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPropertyByIdUseCase {

    private final PropertyGateway propertyGateway;

    public PropertyResponseDTO execute(String id) {

        Property property = propertyGateway.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Property not found"));

        return PropertyResponseDTO.builder()
                .id(property.getId())
                .title(property.getTitle())
                .description(property.getDescription())
                .price(property.getPrice())
                .rooms(property.getRooms())
                .type(property.getType())
                .active(property.getActive())
                .build();
    }
}