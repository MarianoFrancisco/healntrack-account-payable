package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.event.message;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record SurgeryCreatedMessage(

        UUID hospitalizationId,
        UUID surgeryId,
        BigDecimal totalFee,
        LocalDate performedDate

) { }
