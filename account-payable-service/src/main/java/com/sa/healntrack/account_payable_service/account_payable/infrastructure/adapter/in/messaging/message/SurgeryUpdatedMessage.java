package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.messaging.message;

import java.math.BigDecimal;
import java.util.UUID;

public record SurgeryUpdatedMessage(

        UUID hospitalizationId,
        UUID surgeryId,
        BigDecimal totalFee

) { }
