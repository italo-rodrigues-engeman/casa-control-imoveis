package com.indux.realestate.modules.realestate.persistence.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "favorites")
public class FavoriteModel {

    @Id
    private String id;

    private String userId;
    private String propertyId;
}