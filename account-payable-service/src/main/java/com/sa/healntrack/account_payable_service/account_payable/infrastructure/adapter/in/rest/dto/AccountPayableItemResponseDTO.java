package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record AccountPayableItemResponseDTO(

        UUID id,
        String serviceType,
        UUID referenceId,
        BigDecimal fee,
        LocalDate serviceDate

) { }
