package com.sa.healntrack.account_payable_service.account_payable.application.port.in.create_account_payable;

import java.util.UUID;

public record CreateAccountPayableCommand(

        UUID hospitalizationId,
        UUID patientId

) { }
