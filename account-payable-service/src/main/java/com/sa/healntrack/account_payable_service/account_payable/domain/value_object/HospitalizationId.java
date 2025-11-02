package com.sa.healntrack.account_payable_service.account_payable.domain.value_object;

import java.util.UUID;

public record HospitalizationId(UUID value) {
    
    public HospitalizationId {
        if (value == null) {
            throw new IllegalArgumentException("El id de la cuenta a pagar no puede ser nulo");
        }
    }

}
