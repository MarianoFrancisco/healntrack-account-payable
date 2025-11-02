package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.rest.dto;

import java.util.UUID;

import jakarta.validation.constraints.Pattern;

public record SearchAccountsPayableRequestDTO(

    UUID hospitalizationId,

    @Pattern(regexp = "PENDING|CLOSED", message = "El estado solo puede ser PENDING o CLOSED")
    String status

) { }
