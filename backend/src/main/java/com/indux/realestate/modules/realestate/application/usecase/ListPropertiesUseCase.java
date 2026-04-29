package com.indux.realestate.modules.realestate.application.usecase;

import com.indux.realestate.modules.realestate.application.dto.request.PropertyFilterRequest;
import com.indux.realestate.modules.realestate.application.dto.response.PropertyResponseDTO;
import com.indux.realestate.modules.realestate.domain.gateway.PropertyGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListPropertiesUseCase {

    private final PropertyGateway propertyGateway;

    public List<PropertyResponseDTO> execute(PropertyFilterRequest filter) {
        return propertyGateway.findAll()
                .stream()
                .filter(property -> filter.getType() == null || property.getType().equalsIgnoreCase(filter.getType()))
                .filter(property -> filter.getMinPrice() == null || property.getPrice() >= filter.getMinPrice())
                .filter(property -> filter.getMaxPrice() == null || property.getPrice() <= filter.getMaxPrice())
                .filter(property -> filter.getMinRooms() == null || property.getRooms() >= filter.getMinRooms())
                .filter(property -> filter.getTitle() == null || property.getTitle().toLowerCase().contains(filter.getTitle().toLowerCase()))
                .map(property -> PropertyResponseDTO.builder()
                        .id(property.getId())
                        .title(property.getTitle())
                        .description(property.getDescription())
                        .price(property.getPrice())
                        .rooms(property.getRooms())
                        .type(property.getType())
                        .active(property.getActive())
                        .build())
                .toList();
    }
}