package com.sa.healntrack.account_payable_service.account_payable.application.port.in.get_all_accounts_payable;

import java.util.List;

import com.sa.healntrack.account_payable_service.account_payable.domain.AccountPayable;

public interface GetAllAccountsPayable {
    
    List<AccountPayable> getAll(GetAllAccountsPayableQuery query);

}
