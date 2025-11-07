package com.sa.healntrack.account_payable_service.account_payable.application.port.out.messaging;

import java.util.List;

import com.sa.healntrack.account_payable_service.account_payable.domain.AccountPayableItem;

public interface PublishAccountPayableClosed {
    
    void publishClosedMessage(List<AccountPayableItem> items);

}
