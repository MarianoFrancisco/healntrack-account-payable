package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.event.message;

import java.util.UUID;

public record HospitalizationCreatedMessage(

    UUID hospitalizationId

) { }
