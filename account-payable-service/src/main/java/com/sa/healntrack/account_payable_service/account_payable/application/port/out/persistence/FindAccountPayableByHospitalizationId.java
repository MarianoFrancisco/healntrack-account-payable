package com.sa.healntrack.account_payable_service.account_payable.application.port.out.persistence;

import java.util.Optional;
import java.util.UUID;

import com.sa.healntrack.account_payable_service.account_payable.domain.AccountPayable;

public interface FindAccountPayableByHospitalizationId {
    
    Optional<AccountPayable> findByHospitalizationId(UUID hospitalizationId);

}
