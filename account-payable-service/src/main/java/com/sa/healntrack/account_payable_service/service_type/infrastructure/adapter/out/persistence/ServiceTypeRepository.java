package com.sa.healntrack.account_payable_service.service_type.infrastructure.adapter.out.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.account_payable_service.service_type.application.port.out.persistence.FindServiceTypeByName;
import com.sa.healntrack.account_payable_service.service_type.domain.ServiceType;
import com.sa.healntrack.account_payable_service.service_type.infrastructure.adapter.out.persistence.mapper.ServiceTypePersistenceMapper;
import com.sa.healntrack.account_payable_service.service_type.infrastructure.adapter.out.persistence.repository.ServiceTypeEntityRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ServiceTypeRepository implements FindServiceTypeByName {

    private final ServiceTypePersistenceMapper mapper;
    private final ServiceTypeEntityRepository repository;

    @Transactional(readOnly = true)
    @Override
    public Optional<ServiceType> findByName(String name) {
        return repository.findByName(name)
                .map(mapper::toDomain);
    }

}
