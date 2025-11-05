package com.sa.healntrack.account_payable_service.account_payable.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.account_payable_service.account_payable.application.mapper.AccountPayableMapper;
import com.sa.healntrack.account_payable_service.account_payable.application.port.in.create_account_payable.CreateAccountPayable;
import com.sa.healntrack.account_payable_service.account_payable.application.port.in.create_account_payable.CreateAccountPayableCommand;
import com.sa.healntrack.account_payable_service.account_payable.application.port.out.persistence.ExistsAccountPayableByHospitalizationId;
import com.sa.healntrack.account_payable_service.account_payable.application.port.out.persistence.SaveAccountPayable;
import com.sa.healntrack.account_payable_service.account_payable.application.port.out.rest.hospitalization.CheckHospitalizationById;
import com.sa.healntrack.account_payable_service.account_payable.domain.AccountPayable;
import com.sa.healntrack.account_payable_service.common.application.exception.EntityAlreadyExistsException;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class CreateAccountPayableImpl implements CreateAccountPayable {

    private final AccountPayableMapper mapper;
    private final CheckHospitalizationById checkHospitalizationById;
    private final ExistsAccountPayableByHospitalizationId existsAccountPayableByHospitalizationId;
    private final SaveAccountPayable saveAccountPayable;
    
    @Override
    public void create(CreateAccountPayableCommand command) {
        checkHospitalizationById.checkById(command.hospitalizationId());
        boolean existsByHospitalizationId = existsAccountPayableByHospitalizationId
                .existsByHospitalizationId(command.hospitalizationId());
        if (existsByHospitalizationId) {
            throw new EntityAlreadyExistsException(
                    "Ya existe una cuenta a pagar para la hospitalizacion con id: "
                    + command.hospitalizationId());
        }
        AccountPayable accountPayable = mapper.toDomain(command);
        saveAccountPayable.save(accountPayable);
    }

}
