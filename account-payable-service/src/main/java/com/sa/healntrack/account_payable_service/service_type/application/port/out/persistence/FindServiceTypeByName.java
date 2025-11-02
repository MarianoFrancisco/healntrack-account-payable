package com.sa.healntrack.account_payable_service.service_type.application.port.out.persistence;

import java.util.Optional;

import com.sa.healntrack.account_payable_service.service_type.domain.ServiceType;

public interface FindServiceTypeByName {
    
    Optional<ServiceType> findByName(String name);

}
