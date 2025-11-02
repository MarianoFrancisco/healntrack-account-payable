package com.sa.healntrack.account_payable_service.account_payable.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sa.healntrack.account_payable_service.account_payable.application.port.in.add_account_payable_item.AddAccountPayableItemCommand;
import com.sa.healntrack.account_payable_service.account_payable.domain.AccountPayableItem;
import com.sa.healntrack.account_payable_service.service_type.domain.ServiceType;

@Mapper(componentModel = "spring")
public interface AccountPayableItemMapper {
    
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    AccountPayableItem toDomain(
            AddAccountPayableItemCommand command, ServiceType serviceType);

}
