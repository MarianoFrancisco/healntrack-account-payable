package com.sa.healntrack.account_payable_service.account_payable.domain.value_object;

import java.util.UUID;

public record PatientId(UUID value) {

    public PatientId {
        if (value == null) {
            throw new IllegalArgumentException("El id del paciente no puede ser nulo");
        }
    }
    
}
