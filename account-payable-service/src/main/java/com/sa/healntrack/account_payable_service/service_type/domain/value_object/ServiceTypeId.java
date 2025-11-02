package com.sa.healntrack.account_payable_service.service_type.domain.value_object;

import java.util.UUID;

public record ServiceTypeId(UUID value) {
 
    public ServiceTypeId {
        if (value == null) {
            throw new IllegalArgumentException("El id del tipo del servicio no puede ser nulo");
        }
    }
    
}
