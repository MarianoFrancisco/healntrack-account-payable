package com.sa.healntrack.account_payable_service.account_payable.application.port.in.add_account_payable_item;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record AddAccountPayableItemCommand(

        UUID hospitalizationId,
        String service,
        UUID referenceId,
        BigDecimal fee,
        LocalDate serviceDate

) { }
