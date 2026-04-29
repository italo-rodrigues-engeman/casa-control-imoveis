package com.indux.realestate.modules.realestate.persistence.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class UserModel {

    @Id
    private String id;

    private String name;
    private String email;
    private String password;
    private String role;
}