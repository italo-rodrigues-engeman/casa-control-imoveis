package com.indux.realestate.modules.realestate.persistence.gateway;

import com.indux.realestate.modules.realestate.domain.entity.Property;
import com.indux.realestate.modules.realestate.domain.gateway.PropertyGateway;
import com.indux.realestate.modules.realestate.persistence.model.PropertyModel;
import com.indux.realestate.modules.realestate.persistence.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PropertyGatewayImpl implements PropertyGateway {

    private final PropertyRepository repository;

    @Override
    public Property save(Property property) {
        PropertyModel model = toModel(property);
        PropertyModel saved = repository.save(model);
        return toEntity(saved);
    }

    @Override
    public Optional<Property> findById(String id) {
        return repository.findById(id)
                .map(this::toEntity);
    }

    @Override
    public List<Property> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deactivate(String id) {
        repository.findById(id).ifPresent(model -> {
            model.setActive(false);
            repository.save(model);
        });
    }

    // ===== MAPPERS MANUAIS (por enquanto) =====

    private PropertyModel toModel(Property entity) {
        return PropertyModel.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .rooms(entity.getRooms())
                .type(entity.getType())
                .active(entity.getActive())
                .createdByUserId(entity.getCreatedByUserId())
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