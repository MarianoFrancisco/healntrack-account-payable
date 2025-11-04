package com.sa.healntrack.account_payable_service.account_payable.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sa.healntrack.account_payable_service.account_payable.application.port.in.create_account_payable.CreateAccountPayableCommand;
import com.sa.healntrack.account_payable_service.account_payable.domain.AccountPayable;

@Mapper(componentModel = "spring")
public interface AccountPayableMapper {
    
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "totalFee", expression = "java(java.math.BigDecimal.ZERO)")
    @Mapping(target = "status", constant = "PENDING")
    @Mapping(target = "closingDate", ignore = true)
    @Mapping(target = "items", ignore = true)
    AccountPayable toDomain(CreateAccountPayableCommand command);

}
