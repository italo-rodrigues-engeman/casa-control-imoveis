package com.indux.realestate.modules.realestate.domain.entity;

import com.indux.realestate.modules.realestate.domain.enums.UserRole;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id;
    private String name;
    private String email;
    private String password;
    private UserRole role;
}