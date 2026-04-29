package com.indux.realestate.modules.realestate.domain.gateway;

import com.indux.realestate.modules.realestate.domain.entity.Favorite;

import java.util.List;

public interface FavoriteGateway {

    Favorite save(Favorite favorite);

    List<Favorite> findByUserId(String userId);
}