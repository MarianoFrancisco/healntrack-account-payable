package com.sa.healntrack.account_payable_service.account_payable.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.account_payable_service.account_payable.application.port.in.close_account_payable.CloseAccountPayable;
import com.sa.healntrack.account_payable_service.account_payable.application.port.out.messaging.PublishAccountPayableClosed;
import com.sa.healntrack.account_payable_service.account_payable.application.port.out.persistence.FindAccountPayableById;
import com.sa.healntrack.account_payable_service.account_payable.application.port.out.persistence.SaveAccountPayable;
import com.sa.healntrack.account_payable_service.account_payable.domain.AccountPayable;
import com.sa.healntrack.account_payable_service.common.application.exception.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class CloseAccountPayableImpl implements CloseAccountPayable {
    
    private final FindAccountPayableById findAccountPayableById;
    private final SaveAccountPayable saveAccountPayable;
    private final PublishAccountPayableClosed publishAccountPayableClosed;

    @Override
    public void close(UUID id) {
        AccountPayable accountPayable = findAccountPayableById
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No existe una cuenta a pagar con id: " + id));
        accountPayable.close();
        AccountPayable accountPayableSaved = saveAccountPayable.save(accountPayable);
        publishAccountPayableClosed
                .publishClosedMessage(accountPayableSaved);
    }

}
