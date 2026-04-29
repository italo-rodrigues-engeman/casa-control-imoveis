package com.indux.realestate.modules.realestate.application.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PropertyResponseDTO {

    private String id;
    private String title;
    private String description;
    private Double price;
    private Integer rooms;
    private String type;
    private Boolean active;
}