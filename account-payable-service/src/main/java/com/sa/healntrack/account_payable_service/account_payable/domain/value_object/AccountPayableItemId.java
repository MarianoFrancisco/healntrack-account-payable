package com.sa.healntrack.account_payable_service.account_payable.domain.value_object;

import java.util.UUID;

public record AccountPayableItemId(UUID value) {
    
    public AccountPayableItemId {
        if (value == null) {
            throw new IllegalArgumentException("El id del item de la cuenta a pagar no puede ser nulo");
        }
    }

}
