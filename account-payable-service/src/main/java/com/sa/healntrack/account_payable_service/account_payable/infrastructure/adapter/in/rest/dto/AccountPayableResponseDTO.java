package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.sa.healntrack.account_payable_service.account_payable.domain.value_object.Status;

public record AccountPayableResponseDTO(

        UUID id,
        UUID hospitalizationId,
        BigDecimal totalFee,
        Status status,
        LocalDate closingDate,
        List<AccountPayableItemResponseDTO> items

) { }
