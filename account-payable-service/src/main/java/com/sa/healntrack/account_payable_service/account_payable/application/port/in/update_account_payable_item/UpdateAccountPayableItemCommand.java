package com.sa.healntrack.account_payable_service.account_payable.application.port.in.update_account_payable_item;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateAccountPayableItemCommand(

        UUID hospitalizationId,
        UUID referenceId,
        BigDecimal fee

) { }
