package com.indux.realestate.modules.realestate.domain.entity;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Favorite {

    private String id;
    private String userId;
    private String propertyId;
}
