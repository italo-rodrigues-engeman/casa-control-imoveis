package com.indux.realestate.modules.realestate.presentation;

import com.indux.realestate.modules.realestate.application.dto.request.FavoritePropertyRequest;
import com.indux.realestate.modules.realestate.application.usecase.FavoritePropertyUseCase;
import com.indux.realestate.modules.realestate.application.usecase.ListFavoritesUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoritePropertyUseCase favoritePropertyUseCase;
    private final ListFavoritesUseCase listFavoritesUseCase;

    @PostMapping
    public ResponseEntity<Void> favorite(@Valid @RequestBody FavoritePropertyRequest request,
                                         Authentication authentication) {

        String userId = authentication.getName();
        favoritePropertyUseCase.execute(request, userId);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> list(Authentication authentication) {

        String userId = authentication.getName();
        return ResponseEntity.ok(listFavoritesUseCase.execute(userId));
    }
}