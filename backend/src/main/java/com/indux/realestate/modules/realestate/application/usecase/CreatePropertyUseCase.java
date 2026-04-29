package com.indux.realestate.modules.realestate.application.usecase;

import com.indux.realestate.modules.realestate.application.dto.request.CreatePropertyRequest;
import com.indux.realestate.modules.realestate.domain.entity.Property;
import com.indux.realestate.modules.realestate.domain.gateway.PropertyGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePropertyUseCase {

    private final PropertyGateway propertyGateway;

    public void execute(CreatePropertyRequest request, String userId) {

        Property property = Property.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .rooms(request.getRooms())
                .type(request.getType())
                .active(true)
                .createdByUserId(userId)
                .build();

        propertyGateway.save(property);
    }
}