package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sa.healntrack.account_payable_service.account_payable.domain.AccountPayableItem;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.rest.dto.AccountPayableItemResponseDTO;

@Mapper(componentModel = "spring")
public interface AccountPayableItemRestMapper {

    @Mapping(target = "id", source = "accountPayableItem.id.value")
    @Mapping(target = "serviceType", source = "accountPayableItem.serviceType.name.value")
    @Mapping(target = "referenceId", source = "accountPayableItem.referenceId.value")
    @Mapping(target = "fee", source = "accountPayableItem.fee.value")
    AccountPayableItemResponseDTO fromDomain(AccountPayableItem accountPayableItem);
    
}
