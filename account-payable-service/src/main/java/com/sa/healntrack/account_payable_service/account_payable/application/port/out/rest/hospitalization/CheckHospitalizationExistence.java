package com.sa.healntrack.account_payable_service.account_payable.application.port.out.rest.hospitalization;

import java.util.UUID;

public interface CheckHospitalizationExistence {
    
    void checkExistence(UUID id);

}
