package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.account_payable_service.account_payable.application.port.in.get_all_accounts_payable.GetAllAccountsPayableQuery;
import com.sa.healntrack.account_payable_service.account_payable.application.port.out.persistence.ExistsAccountPayableByHospitalizationId;
import com.sa.healntrack.account_payable_service.account_payable.application.port.out.persistence.FindAccountPayableByHospitalizationId;
import com.sa.healntrack.account_payable_service.account_payable.application.port.out.persistence.FindAccountPayableById;
import com.sa.healntrack.account_payable_service.account_payable.application.port.out.persistence.FindAllAccountsPayable;
import com.sa.healntrack.account_payable_service.account_payable.application.port.out.persistence.SaveAccountPayable;
import com.sa.healntrack.account_payable_service.account_payable.domain.AccountPayable;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.persistence.entity.AccountPayableEntity;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.persistence.mapper.AccountPayablePersistenceMapper;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.persistence.repository.AccountPayableEntityRepository;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.persistence.specification.AccountPayableSpecs;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class AccountPayableRepository implements SaveAccountPayable,
        ExistsAccountPayableByHospitalizationId, FindAccountPayableByHospitalizationId,
        FindAccountPayableById, FindAllAccountsPayable {
    
    private final AccountPayablePersistenceMapper mapper;
    private final AccountPayableEntityRepository repository;

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void save(AccountPayable accountPayable) {
        repository.save(mapper.fromDomain(accountPayable));
    }

    @Transactional(readOnly = true)
    @Override
    public boolean exists(UUID hospitalizationId) {
        return repository.existsByHospitalizationId(hospitalizationId);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<AccountPayable> findByHospitalizationId(UUID hospitalizationId) {
        return repository.findByHospitalizationId(hospitalizationId)
                .map(mapper::toDomain);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<AccountPayable> findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Transactional(readOnly = true)
    @Override
    public List<AccountPayable> findAll(GetAllAccountsPayableQuery query) {
        Specification<AccountPayableEntity> specs = Specification.allOf(
                AccountPayableSpecs.hasHospitalizationId(query.hospitalizationId()),
                AccountPayableSpecs.hasStatus(query.status()));
        return repository.findAll(specs).stream()
                .map(mapper::toDomain)
                .toList();
    }

}
