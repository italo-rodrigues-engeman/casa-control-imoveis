package com.indux.realestate.modules.realestate.persistence.gateway;

import com.indux.realestate.modules.realestate.domain.entity.Property;
import com.indux.realestate.modules.realestate.domain.gateway.PropertyGateway;
import com.indux.realestate.modules.realestate.persistence.model.PropertyModel;
import com.indux.realestate.modules.realestate.persistence.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PropertyGatewayImpl implements PropertyGateway {

    private final PropertyRepository repository;

    @Override
    public Property save(Property property) {
        PropertyModel saved = repository.save(toModel(property));
        return toEntity(saved);
    }

    @Override
    public Optional<Property> findById(String id) {
        return repository.findById(id).map(this::toEntity);
    }

    @Override
    public List<Property> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toEntity)
                .toList();
    }

    @Override
    public void deactivate(String id) {
        repository.findById(id).ifPresent(model -> {
            model.setActive(false);
            repository.save(model);
        });
    }

    private PropertyModel toModel(Property property) {
        return PropertyModel.builder()
                .id(property.getId())
                .title(property.getTitle())
                .description(property.getDescription())
                .price(property.getPrice())
                .rooms(property.getRooms())
                .type(property.getType())
                .active(property.getActive())
                .createdByUserId(property.getCreatedByUserId())
                .build();
    }

    private Property toEntity(PropertyModel model) {
        return Property.builder()
                .id(model.getId())
                .title(model.getTitle())
                .description(model.getDescription())
                .price(model.getPrice())
                .rooms(model.getRooms())
                .type(model.getType())
                .active(model.getActive())
                .createdByUserId(model.getCreatedByUserId())
                .build();
    }
}