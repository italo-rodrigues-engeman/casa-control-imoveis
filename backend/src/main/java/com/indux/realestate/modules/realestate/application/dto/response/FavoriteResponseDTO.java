package com.indux.realestate.modules.realestate.application.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FavoriteResponseDTO {

    private String id;
    private String propertyId;

    private String title;
    private String description;
    private Double price;
    private Integer rooms;
    private String type;
}