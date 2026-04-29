package com.indux.realestate.modules.realestate.presentation;

import com.indux.realestate.modules.realestate.application.dto.request.CreatePropertyRequest;
import com.indux.realestate.modules.realestate.application.dto.request.PropertyFilterRequest;
import com.indux.realestate.modules.realestate.application.dto.request.UpdatePropertyRequest;
import com.indux.realestate.modules.realestate.application.dto.response.PropertyResponseDTO;
import com.indux.realestate.modules.realestate.application.usecase.CreatePropertyUseCase;
import com.indux.realestate.modules.realestate.application.usecase.GetPropertyByIdUseCase;
import com.indux.realestate.modules.realestate.application.usecase.ListPropertiesUseCase;
import com.indux.realestate.modules.realestate.application.usecase.TogglePropertyStatusUseCase;
import com.indux.realestate.modules.realestate.application.usecase.UpdatePropertyUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final CreatePropertyUseCase createPropertyUseCase;
    private final ListPropertiesUseCase listPropertiesUseCase;
    private final UpdatePropertyUseCase updatePropertyUseCase;
    private final TogglePropertyStatusUseCase togglePropertyStatusUseCase;
    private final GetPropertyByIdUseCase getPropertyByIdUseCase;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody CreatePropertyRequest request,
                                       Authentication authentication) {

        String userId = authentication.getName();
        createPropertyUseCase.execute(request, userId);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<PropertyResponseDTO>> list(@ModelAttribute PropertyFilterRequest filter) {
        return ResponseEntity.ok(listPropertiesUseCase.execute(filter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyResponseDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok(getPropertyByIdUseCase.execute(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id,
                                       @Valid @RequestBody UpdatePropertyRequest request,
                                       Authentication authentication) {

        String userId = authentication.getName();
        updatePropertyUseCase.execute(id, request, userId);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/toggle")
    public ResponseEntity<Void> toggle(@PathVariable String id,
                                       Authentication authentication) {

        String userId = authentication.getName();
        togglePropertyStatusUseCase.execute(id, userId);

        return ResponseEntity.ok().build();
    }
}