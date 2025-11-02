package com.sa.healntrack.account_payable_service.account_payable.application.port.in.remove_account_payable_item;

import java.util.UUID;

public record RemoveAccountPayableItemCommand(

        UUID hospitalizationId,
        UUID referenceId

) { }
