package com.indux.realestate.modules.realestate.application.usecase;

import com.indux.realestate.modules.realestate.application.dto.request.FavoritePropertyRequest;
import com.indux.realestate.modules.realestate.domain.entity.Favorite;
import com.indux.realestate.modules.realestate.domain.gateway.FavoriteGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoritePropertyUseCase {

    private final FavoriteGateway favoriteGateway;

    public void execute(FavoritePropertyRequest request, String userId) {

        Favorite favorite = Favorite.builder()
                .userId(userId)
                .propertyId(request.getPropertyId())
                .build();

        favoriteGateway.save(favorite);
    }
}