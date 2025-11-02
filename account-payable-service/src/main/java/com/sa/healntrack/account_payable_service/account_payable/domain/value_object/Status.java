package com.sa.healntrack.account_payable_service.account_payable.domain.value_object;

public enum Status {

    PENDING,
    CLOSED;

    public static Status fromString(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El genero no puede ser nulo");
        }
        try {
            return Status.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("El genero es invalido");
        }
    }
    
}
