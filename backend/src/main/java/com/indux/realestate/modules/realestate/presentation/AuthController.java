package com.indux.realestate.modules.realestate.presentation;

import com.indux.realestate.modules.realestate.application.dto.request.LoginRequest;
import com.indux.realestate.modules.realestate.application.dto.response.LoginResponse;
import com.indux.realestate.modules.realestate.application.usecase.LoginUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final LoginUseCase loginUseCase;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(loginUseCase.execute(request));
    }
}