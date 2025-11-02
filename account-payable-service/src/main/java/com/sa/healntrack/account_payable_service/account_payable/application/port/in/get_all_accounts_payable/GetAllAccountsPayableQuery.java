package com.sa.healntrack.account_payable_service.account_payable.application.port.in.get_all_accounts_payable;

import java.util.UUID;

import com.sa.healntrack.account_payable_service.account_payable.domain.value_object.Status;

public record GetAllAccountsPayableQuery(

        UUID hospitalizationId,
        Status status

) { }
