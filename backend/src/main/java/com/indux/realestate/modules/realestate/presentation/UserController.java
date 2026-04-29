package com.indux.realestate.modules.realestate.presentation;

import com.indux.realestate.modules.realestate.application.dto.request.CreateUserRequest;
import com.indux.realestate.modules.realestate.application.usecase.CreateUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody CreateUserRequest request) {
        createUserUseCase.execute(request);
        return ResponseEntity.ok().build();
    }
}