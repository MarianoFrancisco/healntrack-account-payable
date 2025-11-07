package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.messaging.mapper;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sa.healntrack.account_payable_service.account_payable.domain.AccountPayableItem;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.messaging.message.AccountPayableClosedMessage;

@Mapper(componentModel = "spring")
public interface AccountPayableOutMessagingMapper {
    
    @Mapping(target = "service", source = "item.serviceType.name.value")
    AccountPayableClosedMessage toClosedMessage(AccountPayableItem item, UUID patientId);

}
