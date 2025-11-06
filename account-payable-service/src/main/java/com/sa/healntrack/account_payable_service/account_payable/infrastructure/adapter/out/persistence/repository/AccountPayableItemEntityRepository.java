package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.persistence.entity.AccountPayableItemEntity;

public interface AccountPayableItemEntityRepository extends JpaRepository<AccountPayableItemEntity, UUID> {
    
    boolean existsByReferenceId(UUID referenceId);

}
