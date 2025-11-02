package com.sa.healntrack.account_payable_service.account_payable.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.account_payable_service.account_payable.application.port.in.get_all_accounts_payable.GetAllAccountsPayable;
import com.sa.healntrack.account_payable_service.account_payable.application.port.in.get_all_accounts_payable.GetAllAccountsPayableQuery;
import com.sa.healntrack.account_payable_service.account_payable.application.port.out.persistence.FindAllAccountsPayable;
import com.sa.healntrack.account_payable_service.account_payable.domain.AccountPayable;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class GetAllAccountsPayableImpl implements GetAllAccountsPayable {

    private final FindAllAccountsPayable findAllAccountsPayable;

    @Override
    public List<AccountPayable> getAll(GetAllAccountsPayableQuery query) {
        return findAllAccountsPayable.findAll(query);
    }
    
    
}
