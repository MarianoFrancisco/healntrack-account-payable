package com.sa.healntrack.account_payable_service.account_payable.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.account_payable_service.account_payable.application.mapper.AccountPayableItemMapper;
import com.sa.healntrack.account_payable_service.account_payable.application.port.in.add_account_payable_item.AddAccountPayableItem;
import com.sa.healntrack.account_payable_service.account_payable.application.port.in.add_account_payable_item.AddAccountPayableItemCommand;
import com.sa.healntrack.account_payable_service.account_payable.application.port.out.persistence.ExistsAccountPayableItemByReferenceId;
import com.sa.healntrack.account_payable_service.account_payable.application.port.out.persistence.FindAccountPayableByHospitalizationId;
import com.sa.healntrack.account_payable_service.account_payable.application.port.out.persistence.SaveAccountPayable;
import com.sa.healntrack.account_payable_service.account_payable.domain.AccountPayable;
import com.sa.healntrack.account_payable_service.account_payable.domain.AccountPayableItem;
import com.sa.healntrack.account_payable_service.common.application.exception.EntityAlreadyExistsException;
import com.sa.healntrack.account_payable_service.common.application.exception.EntityNotFoundException;
import com.sa.healntrack.account_payable_service.service_type.application.port.out.persistence.FindServiceTypeByName;
import com.sa.healntrack.account_payable_service.service_type.domain.ServiceType;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class AddAccountPayableItemImpl implements AddAccountPayableItem {
    
    private final AccountPayableItemMapper mapper;
    private final FindServiceTypeByName findServiceTypeByName;
    private final FindAccountPayableByHospitalizationId findAccountPayableByHospitalizationId;
    private final ExistsAccountPayableItemByReferenceId existsAccountPayableItemByReferenceId;
    private final SaveAccountPayable saveAccountPayable;

    @Override
    public void create(AddAccountPayableItemCommand command) {
        ServiceType serviceType = findServiceTypeByName
                .findByName(command.service())
                .orElseThrow(() -> new EntityNotFoundException(
                        "No existe un tipo de servicio con el nombre: " + command.service()));
        AccountPayable accountPayable = findAccountPayableByHospitalizationId
                .findByHospitalizationId(command.hospitalizationId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "No existe una cuenta a pagar asociada a la hospitalizacion con id: "
                        + command.hospitalizationId()));
        boolean existsItemByReferenceId = existsAccountPayableItemByReferenceId
                .existsByReferenceId(command.referenceId());
        if (existsItemByReferenceId) {
            throw new EntityAlreadyExistsException(
                    "Ya existe un item cuya referencia tiene el id: " + command.referenceId());
        }
        AccountPayableItem accountPayableItem = mapper.toDomain(command, serviceType);
        accountPayable.addItem(accountPayableItem);
        saveAccountPayable.save(accountPayable);
    }

}
