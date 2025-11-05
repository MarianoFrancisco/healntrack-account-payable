package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.event.mapper;

import org.mapstruct.Mapper;

import com.sa.healntrack.account_payable_service.account_payable.application.port.in.create_account_payable.CreateAccountPayableCommand;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.event.message.HospitalizationCreatedMessage;

@Mapper(componentModel = "spring")
public interface AccountPayableEventMapper {
    
    CreateAccountPayableCommand toCommand(HospitalizationCreatedMessage message);

}
