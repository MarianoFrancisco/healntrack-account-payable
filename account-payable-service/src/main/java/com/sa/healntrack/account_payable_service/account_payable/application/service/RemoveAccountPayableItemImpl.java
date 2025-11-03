package com.sa.healntrack.account_payable_service.account_payable.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.account_payable_service.account_payable.application.port.in.remove_account_payable_item.RemoveAccountPayableItem;
import com.sa.healntrack.account_payable_service.account_payable.application.port.in.remove_account_payable_item.RemoveAccountPayableItemCommand;
import com.sa.healntrack.account_payable_service.account_payable.application.port.out.persistence.ExistsAccountPayableItemByReferenceId;
import com.sa.healntrack.account_payable_service.account_payable.application.port.out.persistence.FindAccountPayableByHospitalizationId;
import com.sa.healntrack.account_payable_service.account_payable.application.port.out.persistence.SaveAccountPayable;
import com.sa.healntrack.account_payable_service.account_payable.domain.AccountPayable;
import com.sa.healntrack.account_payable_service.common.application.exception.EntityAlreadyExistsException;
import com.sa.healntrack.account_payable_service.common.application.exception.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class RemoveAccountPayableItemImpl implements RemoveAccountPayableItem {
    
    private final FindAccountPayableByHospitalizationId findAccountPayableByHospitalizationId;
    private final ExistsAccountPayableItemByReferenceId existsAccountPayableItemByReferenceId;
    private final SaveAccountPayable saveAccountPayable;

    @Override
    public void remove(RemoveAccountPayableItemCommand command) {
        AccountPayable accountPayable = findAccountPayableByHospitalizationId
                .findByHospitalizationId(command.hospitalizationId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "No existe una cuenta a pagar asociada a la hospitalizacion con id: "
                        + command.hospitalizationId()));
        boolean exists = existsAccountPayableItemByReferenceId
                .existsByReferenceId(command.referenceId());
        if (!exists) {
            throw new EntityAlreadyExistsException(
                    "No existe un item cuya referencia tenga el id: " + command.referenceId());
        }
        accountPayable.removeItem(command.referenceId());
        saveAccountPayable.save(accountPayable);
    }

}
