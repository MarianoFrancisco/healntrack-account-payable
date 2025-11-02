package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sa.healntrack.account_payable_service.account_payable.domain.AccountPayableItem;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.persistence.entity.AccountPayableItemEntity;
import com.sa.healntrack.account_payable_service.service_type.infrastructure.adapter.out.persistence.mapper.ServiceTypePersistenceMapper;

@Mapper(componentModel = "spring",
        uses = { ServiceTypePersistenceMapper.class })
public interface AccountPayableItemPersistenceMapper {
    
    AccountPayableItem toDomain(AccountPayableItemEntity entity);

    @Mapping(target = "id", source = "accountPayableItem.id.value")
    @Mapping(target = "referenceId", source = "accountPayableItem.referenceId.value")
    @Mapping(target = "fee", source = "accountPayableItem.fee.value")
    @Mapping(target = "accountPayable", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    AccountPayableItemEntity fromDomain(AccountPayableItem accountPayableItem);

}
