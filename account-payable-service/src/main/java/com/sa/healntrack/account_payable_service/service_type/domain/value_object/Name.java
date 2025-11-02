package com.sa.healntrack.account_payable_service.service_type.domain.value_object;

public record Name(String value) {
    
    public Name {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El nombre del servicio no puede ser nulo");
        }
    }

}
