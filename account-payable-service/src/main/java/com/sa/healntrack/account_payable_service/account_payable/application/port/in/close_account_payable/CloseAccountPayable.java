package com.sa.healntrack.account_payable_service.account_payable.application.port.in.close_account_payable;

import java.util.UUID;

public interface CloseAccountPayable {
    
    void close(UUID id);

}
