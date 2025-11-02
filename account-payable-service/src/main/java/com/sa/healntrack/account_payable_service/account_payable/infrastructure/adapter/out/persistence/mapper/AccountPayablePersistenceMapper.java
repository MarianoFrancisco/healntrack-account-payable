package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sa.healntrack.account_payable_service.account_payable.domain.AccountPayable;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.persistence.entity.AccountPayableEntity;

@Mapper(componentModel = "spring", uses = { AccountPayableItemPersistenceMapper.class })
public interface AccountPayablePersistenceMapper {
    
    AccountPayable toDomain(AccountPayableEntity entity);

    @Mapping(target = "id", source = "accountPayable.id.value")
    @Mapping(target = "hospitalizationId", source = "accountPayable.hospitalizationId.value")
    @Mapping(target = "totalFee", source = "accountPayable.totalFee.value")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    AccountPayableEntity fromDomain(AccountPayable accountPayable);

}
