package com.indux.realestate.modules.realestate.persistence.gateway;

import com.indux.realestate.modules.realestate.domain.entity.Favorite;
import com.indux.realestate.modules.realestate.domain.gateway.FavoriteGateway;
import com.indux.realestate.modules.realestate.persistence.model.FavoriteModel;
import com.indux.realestate.modules.realestate.persistence.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FavoriteGatewayImpl implements FavoriteGateway {

    private final FavoriteRepository repository;

    @Override
    public Favorite save(Favorite favorite) {
        FavoriteModel saved = repository.save(toModel(favorite));
        return toEntity(saved);
    }

    @Override
    public List<Favorite> findByUserId(String userId) {
        return repository.findByUserId(userId)
                .stream()
                .map(this::toEntity)
                .toList();
    }

    private FavoriteModel toModel(Favorite favorite) {
        return FavoriteModel.builder()
                .id(favorite.getId())
                .userId(favorite.getUserId())
                .propertyId(favorite.getPropertyId())
                .build();
    }

    private Favorite toEntity(FavoriteModel model) {
        return Favorite.builder()
                .id(model.getId())
                .userId(model.getUserId())
                .propertyId(model.getPropertyId())
                .build();
    }
}