package com.indux.realestate.modules.realestate.application.usecase;

import com.indux.realestate.modules.realestate.domain.gateway.PropertyGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeactivatePropertyUseCase {

    private final PropertyGateway propertyGateway;

    public void execute(String id) {
        propertyGateway.deactivate(id);
    }
}