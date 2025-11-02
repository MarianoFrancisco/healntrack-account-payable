package com.sa.healntrack.account_payable_service.account_payable.application.port.out.persistence;

import java.util.List;

import com.sa.healntrack.account_payable_service.account_payable.application.port.in.get_all_accounts_payable.GetAllAccountsPayableQuery;
import com.sa.healntrack.account_payable_service.account_payable.domain.AccountPayable;

public interface FindAllAccountsPayable {
    
    List<AccountPayable> findAll(GetAllAccountsPayableQuery query);

}
