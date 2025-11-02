package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sa.healntrack.account_payable_service.account_payable.application.port.in.get_all_accounts_payable.GetAllAccountsPayableQuery;
import com.sa.healntrack.account_payable_service.account_payable.domain.AccountPayable;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.rest.dto.AccountPayableResponseDTO;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.rest.dto.SearchAccountsPayableRequestDTO;

@Mapper(componentModel = "spring", uses = { AccountPayableItemRestMapper.class })
public interface AccountPayableRestMapper {
    
    @Mapping(target = "id", source = "accountPayable.id.value")
    @Mapping(target = "hospitalizationId", source = "accountPayable.hospitalizationId.value")
    @Mapping(target = "totalFee", source = "accountPayable.totalFee.value")
    AccountPayableResponseDTO fromDomain(AccountPayable accountPayable);

    GetAllAccountsPayableQuery toQuery(SearchAccountsPayableRequestDTO requestDTO);

}
