package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.persistence;

import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.account_payable_service.account_payable.application.port.out.persistence.ExistsAccountPayableItemByReferenceId;
import com.sa.healntrack.account_payable_service.account_payable.application.port.out.persistence.SaveAccountPayableItem;
import com.sa.healntrack.account_payable_service.account_payable.domain.AccountPayableItem;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.persistence.entity.AccountPayableItemEntity;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.persistence.mapper.AccountPayableItemPersistenceMapper;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.persistence.repository.AccountPayableItemEntityRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class AccountPayableItemRepository implements ExistsAccountPayableItemByReferenceId, SaveAccountPayableItem {

    private final AccountPayableItemPersistenceMapper mapper;
    private final AccountPayableItemEntityRepository repository;

    @Transactional(readOnly = true)
    @Override
    public boolean existsByReferenceId(UUID referendeId) {
        return repository.existsByReferenceId(referendeId);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public AccountPayableItem save(AccountPayableItem accountPayableItem) {
        AccountPayableItemEntity entity = repository
                .save(mapper.fromDomain(accountPayableItem));
        return mapper.toDomain(entity);
    }

}
