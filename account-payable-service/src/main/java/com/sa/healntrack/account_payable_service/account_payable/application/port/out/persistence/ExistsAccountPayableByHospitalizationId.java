package com.sa.healntrack.account_payable_service.account_payable.application.port.out.persistence;

import java.util.UUID;

public interface ExistsAccountPayableByHospitalizationId {

    boolean existsByHospitalizationId(UUID hospitalizationId);
    
}
