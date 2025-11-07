package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.rest.dto;

import java.time.LocalDate;
import java.util.UUID;

public record HospitalizationResponseDTO(

        UUID id,
        LocalDate dischargeDate

) { }
