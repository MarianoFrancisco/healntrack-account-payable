package com.sa.healntrack.account_payable_service.account_payable.application.port.out.messaging;


import com.sa.healntrack.account_payable_service.account_payable.domain.AccountPayable;

public interface PublishAccountPayableClosed {
    
    void publishClosedMessage(AccountPayable accountPayable);

}
