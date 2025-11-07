package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.messaging.message;

import java.util.UUID;

public record AccountPayableClosedMessage(

        UUID referenceId,
        UUID patientId,
        String service

) { }
