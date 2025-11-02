package com.sa.healntrack.account_payable_service.service_type.infrastructure.adapter.out.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.healntrack.account_payable_service.service_type.infrastructure.adapter.out.persistence.entity.ServiceTypeEntity;

public interface ServiceTypeEntityRepository extends JpaRepository<ServiceTypeEntity, UUID> {

    Optional<ServiceTypeEntity> findByName(String name);
    
}
