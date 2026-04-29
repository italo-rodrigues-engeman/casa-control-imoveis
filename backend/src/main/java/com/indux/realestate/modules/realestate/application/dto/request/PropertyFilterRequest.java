package com.indux.realestate.modules.realestate.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyFilterRequest {

    private String type;
    private Double minPrice;
    private Double maxPrice;
    private Integer minRooms;
    private String title;

    private Integer page;
    private Integer size;
    private String sortBy;
}