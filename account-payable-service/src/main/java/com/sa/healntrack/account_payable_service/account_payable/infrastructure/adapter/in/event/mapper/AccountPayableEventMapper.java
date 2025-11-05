package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.event.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sa.healntrack.account_payable_service.account_payable.application.port.in.add_account_payable_item.AddAccountPayableItemCommand;
import com.sa.healntrack.account_payable_service.account_payable.application.port.in.create_account_payable.CreateAccountPayableCommand;
import com.sa.healntrack.account_payable_service.account_payable.application.port.in.remove_account_payable_item.RemoveAccountPayableItemCommand;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.event.message.HospitalizationCreatedMessage;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.event.message.PatientDischargedMessage;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.event.message.SurgeryCreatedMessage;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.event.message.SurgeryUpdatedMessage;

@Mapper(componentModel = "spring")
public interface AccountPayableEventMapper {
    
    CreateAccountPayableCommand toCommand(HospitalizationCreatedMessage message);

    @Mapping(target = "service", constant = "STAY")
    @Mapping(target = "referenceId", source = "message.hospitalizationId")
    @Mapping(target = "fee", source = "message.totalFee")
    @Mapping(target = "serviceDate", source = "message.dischargeDate")
    AddAccountPayableItemCommand toCommand(PatientDischargedMessage message);

    @Mapping(target = "service", constant = "SURG")
    @Mapping(target = "referenceId", source = "message.surgeryId")
    @Mapping(target = "fee", source = "message.totalFee")
    @Mapping(target = "serviceDate", source = "message.performedDate")
    AddAccountPayableItemCommand toCommand(SurgeryCreatedMessage message);

    @Mapping(target = "service", constant = "SURG")
    @Mapping(target = "referenceId", source = "message.surgeryId")
    @Mapping(target = "fee", source = "message.totalFee")
    @Mapping(target = "serviceDate", source = "message.performedDate")
    AddAccountPayableItemCommand toCommand(SurgeryUpdatedMessage message);

    @Mapping(target = "referenceId", source = "message.surgeryId")
    RemoveAccountPayableItemCommand toRCommand(SurgeryUpdatedMessage message);

}
