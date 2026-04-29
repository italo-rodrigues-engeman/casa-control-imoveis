package com.indux.realestate.modules.realestate.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoritePropertyRequest {

    @NotBlank
    private String propertyId;
}