package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.persistence.entity.AccountPayableEntity;

public interface AccountPayableEntityRepository extends JpaRepository<AccountPayableEntity, UUID>, JpaSpecificationExecutor<AccountPayableEntity> {
    
    boolean existsByHospitalizationId(UUID hospitalizationId);

    Optional<AccountPayableEntity> findByHospitalizationId(UUID hospitalizationId);

}
