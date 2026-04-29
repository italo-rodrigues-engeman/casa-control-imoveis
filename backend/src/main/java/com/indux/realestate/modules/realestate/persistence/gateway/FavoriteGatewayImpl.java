package com.indux.realestate.modules.realestate.persistence.gateway;

import com.indux.realestate.modules.realestate.domain.entity.Favorite;
import com.indux.realestate.modules.realestate.domain.gateway.FavoriteGateway;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class FavoriteGatewayImpl implements FavoriteGateway {

    private final Map<String, Favorite> database = new ConcurrentHashMap<>();

    @Override
    public Favorite save(Favorite favorite) {
        if (favorite.getId() == null) {
            favorite.setId(UUID.randomUUID().toString());
        }

        database.put(favorite.getId(), favorite);
        return favorite;
    }

    @Override
    public List<Favorite> findByUserId(String userId) {
        return database.values()
                .stream()
                .filter(favorite -> favorite.getUserId().equals(userId))
                .toList();
    }
}