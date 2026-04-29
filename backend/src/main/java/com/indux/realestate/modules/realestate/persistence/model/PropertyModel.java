package com.indux.realestate.modules.realestate.persistence.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "properties")
public class PropertyModel {

    @Id
    private String id;

    private String title;
    private String description;
    private Double price;
    private Integer rooms;
    private String type;
    private Boolean active;
    private String createdByUserId;
}