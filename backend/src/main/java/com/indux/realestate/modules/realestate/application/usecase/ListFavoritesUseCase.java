package com.indux.realestate.modules.realestate.application.usecase;

import com.indux.realestate.modules.realestate.application.dto.response.FavoriteResponseDTO;
import com.indux.realestate.modules.realestate.domain.gateway.FavoriteGateway;
import com.indux.realestate.modules.realestate.domain.gateway.PropertyGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListFavoritesUseCase {

    private final FavoriteGateway favoriteGateway;
    private final PropertyGateway propertyGateway;

    public List<FavoriteResponseDTO> execute(String userId) {
        return favoriteGateway.findByUserId(userId)
                .stream()
                .map(favorite -> {
                    var property = propertyGateway.findById(favorite.getPropertyId())
                            .orElse(null);

                    return FavoriteResponseDTO.builder()
                            .id(favorite.getId())
                            .propertyId(favorite.getPropertyId())
                            .title(property != null ? property.getTitle() : null)
                            .description(property != null ? property.getDescription() : null)
                            .price(property != null ? property.getPrice() : null)
                            .rooms(property != null ? property.getRooms() : null)
                            .type(property != null ? property.getType() : null)
                            .build();
                })
                .toList();
    }
}