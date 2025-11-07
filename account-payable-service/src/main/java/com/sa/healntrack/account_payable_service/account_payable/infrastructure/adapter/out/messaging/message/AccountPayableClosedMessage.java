package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.messaging.message;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record AccountPayableClosedMessage(

        UUID patientId,
        String service,
        LocalDate serviceDate,
        BigDecimal fee

) { }
