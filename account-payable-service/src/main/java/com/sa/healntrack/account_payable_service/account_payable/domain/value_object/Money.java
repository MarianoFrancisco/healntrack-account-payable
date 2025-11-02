package com.sa.healntrack.account_payable_service.account_payable.domain.value_object;

import java.math.BigDecimal;

public record Money(BigDecimal value) {

    private static final BigDecimal MIN = new BigDecimal(0.00);

    public Money {
        if (value == null) {
            throw new IllegalArgumentException("El monto no puede ser nulo");
        }
        if (value.compareTo(MIN) < 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a " + MIN);
        }
    }
    
}
