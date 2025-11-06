package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.messaging.message;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record MedicationCreatedMessage(

        UUID saleId,
        UUID buyerId,
        LocalDate ocurredAt,
        BigDecimal total

) { }
