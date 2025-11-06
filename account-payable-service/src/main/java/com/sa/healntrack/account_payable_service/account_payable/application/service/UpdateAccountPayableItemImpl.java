package com.sa.healntrack.account_payable_service.account_payable.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.account_payable_service.account_payable.application.port.in.update_account_payable_item.UpdateAccountPayableItem;
import com.sa.healntrack.account_payable_service.account_payable.application.port.in.update_account_payable_item.UpdateAccountPayableItemCommand;
import com.sa.healntrack.account_payable_service.account_payable.application.port.out.persistence.FindAccountPayableByHospitalizationId;
import com.sa.healntrack.account_payable_service.account_payable.application.port.out.persistence.SaveAccountPayableItem;
import com.sa.healntrack.account_payable_service.account_payable.domain.AccountPayable;
import com.sa.healntrack.account_payable_service.account_payable.domain.AccountPayableItem;
import com.sa.healntrack.account_payable_service.common.application.exception.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class UpdateAccountPayableItemImpl implements UpdateAccountPayableItem {
    
    private final FindAccountPayableByHospitalizationId findAccountPayableByHospitalizationId;
    private final SaveAccountPayableItem saveAccountPayableItem;

    @Override
    public void update(UpdateAccountPayableItemCommand command) {
        AccountPayable accountPayable = findAccountPayableByHospitalizationId
                .findByHospitalizationId(command.hospitalizationId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "No existe una cuenta a pagar asociada a la hospitalizacion con id: "
                        + command.hospitalizationId()));
        accountPayable.validateIsClosed();
        AccountPayableItem accountPayableItem = accountPayable.getItems()
                .stream()
                .filter(item -> item.getReferenceId().value().equals(command.referenceId()))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(
                        "No existe un item cuya referencia tiene el id: " + command.referenceId()));
        accountPayableItem.updateFee(command.fee());
        saveAccountPayableItem.save(accountPayableItem);
    }


}
