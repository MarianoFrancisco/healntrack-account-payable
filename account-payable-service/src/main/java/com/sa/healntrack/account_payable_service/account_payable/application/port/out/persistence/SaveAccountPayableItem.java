package com.sa.healntrack.account_payable_service.account_payable.application.port.out.persistence;

import com.sa.healntrack.account_payable_service.account_payable.domain.AccountPayableItem;

public interface SaveAccountPayableItem {
    
    AccountPayableItem save(AccountPayableItem accountPayableItem);
    
}
