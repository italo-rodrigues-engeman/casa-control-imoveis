package com.indux.realestate.modules.realestate.domain.entity;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Property {

    private String id;
    private String title;
    private String description;
    private Double price;
    private Integer rooms;
    private String type;
    private Boolean active;
    private String createdByUserId;
}