package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.account_payable_service.account_payable.application.port.out.persistence.ExistsAccountPayableItemByReferenceId;
import com.sa.healntrack.account_payable_service.account_payable.application.port.out.persistence.FindAccountPayableItemByReferenceId;
import com.sa.healntrack.account_payable_service.account_payable.domain.AccountPayableItem;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.persistence.mapper.AccountPayableItemPersistenceMapper;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.persistence.repository.AccountPayableItemEntityRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class AccountPayableItemRepository implements ExistsAccountPayableItemByReferenceId, FindAccountPayableItemByReferenceId {

    private final AccountPayableItemPersistenceMapper mapper;
    private final AccountPayableItemEntityRepository repository;

    @Transactional(readOnly = true)
    @Override
    public boolean existsByReferenceId(UUID referendeId) {
        return repository.existsByReferenceId(referendeId);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<AccountPayableItem> findByReferenceId(UUID referenceId) {
        return repository.findByReferenceId(referenceId)
                .map(mapper::toDomain);
    }

}
