package com.sa.healntrack.account_payable_service.account_payable.domain.value_object;

import java.util.UUID;

public record ReferenceId(UUID value) {
    
    public ReferenceId {
        if (value == null) {
            throw new IllegalArgumentException("El id de la referencia no puede ser nulo");
        }
    }

}
