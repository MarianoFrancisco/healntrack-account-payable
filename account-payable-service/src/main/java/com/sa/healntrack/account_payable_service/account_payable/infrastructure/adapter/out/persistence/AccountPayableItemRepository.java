package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.persistence;

import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.account_payable_service.account_payable.application.port.out.persistence.ExistsAccountPayableItemByReferenceId;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.persistence.repository.AccountPayableItemEntityRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class AccountPayableItemRepository implements ExistsAccountPayableItemByReferenceId {

    private final AccountPayableItemEntityRepository repository;

    @Transactional(readOnly = true)
    @Override
    public boolean existsByReferenceId(UUID referendeId) {
        return repository.existsByReferenceId(referendeId);
    }

}
