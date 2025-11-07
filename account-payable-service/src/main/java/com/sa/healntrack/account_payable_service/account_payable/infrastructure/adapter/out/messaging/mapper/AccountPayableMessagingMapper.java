package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.messaging.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sa.healntrack.account_payable_service.account_payable.domain.AccountPayableItem;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.messaging.message.AccountPayableClosedMessage;

@Mapper(componentModel = "spring")
public interface AccountPayableMessagingMapper {
    
    @Mapping(target = "referenceId", source = "item.referenceId.value")
    AccountPayableClosedMessage toClosedMessage(AccountPayableItem item);

}
