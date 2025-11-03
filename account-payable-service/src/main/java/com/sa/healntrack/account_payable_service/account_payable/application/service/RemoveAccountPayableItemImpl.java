package com.sa.healntrack.account_payable_service.account_payable.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.account_payable_service.account_payable.application.port.in.remove_account_payable_item.RemoveAccountPayableItem;
import com.sa.healntrack.account_payable_service.account_payable.application.port.in.remove_account_payable_item.RemoveAccountPayableItemCommand;
import com.sa.healntrack.account_payable_service.account_payable.application.port.out.persistence.FindAccountPayableByHospitalizationId;
import com.sa.healntrack.account_payable_service.account_payable.application.port.out.persistence.FindAccountPayableItemByReferenceId;
import com.sa.healntrack.account_payable_service.account_payable.application.port.out.persistence.SaveAccountPayable;
import com.sa.healntrack.account_payable_service.account_payable.domain.AccountPayable;
import com.sa.healntrack.account_payable_service.account_payable.domain.AccountPayableItem;
import com.sa.healntrack.account_payable_service.common.application.exception.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class RemoveAccountPayableItemImpl implements RemoveAccountPayableItem {
    
    private final FindAccountPayableByHospitalizationId findAccountPayableByHospitalizationId;
    private final FindAccountPayableItemByReferenceId findAccountPayableItemByReferenceId;
    private final SaveAccountPayable saveAccountPayable;

    @Override
    public void remove(RemoveAccountPayableItemCommand command) {
        AccountPayable accountPayable = findAccountPayableByHospitalizationId
                .findByHospitalizationId(command.hospitalizationId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "No existe una cuenta a pagar asociada a la hospitalizacion con id: "
                        + command.hospitalizationId()));
        AccountPayableItem accountPayableItem = findAccountPayableItemByReferenceId
                .findByReferenceId(command.referenceId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "No existe un item cuya referencia tenga el id: "
                        + command.referenceId()));
        accountPayable.removeItem(accountPayableItem.getReferenceId());
        saveAccountPayable.save(accountPayable);
    }

}
