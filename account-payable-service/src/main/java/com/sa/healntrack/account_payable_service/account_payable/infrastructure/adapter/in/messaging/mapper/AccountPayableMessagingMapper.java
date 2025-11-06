package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.messaging.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sa.healntrack.account_payable_service.account_payable.application.port.in.add_account_payable_item.AddAccountPayableItemCommand;
import com.sa.healntrack.account_payable_service.account_payable.application.port.in.create_account_payable.CreateAccountPayableCommand;
import com.sa.healntrack.account_payable_service.account_payable.application.port.in.update_account_payable_item.UpdateAccountPayableItemCommand;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.messaging.message.HospitalizationCreatedMessage;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.messaging.message.MedicationCreatedMessage;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.messaging.message.PatientDischargedMessage;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.messaging.message.SurgeryCreatedMessage;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.messaging.message.SurgeryUpdatedMessage;

@Mapper(componentModel = "spring")
public interface AccountPayableMessagingMapper {
    
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

    @Mapping(target = "referenceId", source = "message.surgeryId")
    @Mapping(target = "fee", source = "message.totalFee")
    UpdateAccountPayableItemCommand toCommand(SurgeryUpdatedMessage message);

    @Mapping(target = "service", constant = "MED")
    @Mapping(target = "hospitalizationId", source = "message.buyerId")
    @Mapping(target = "referenceId", source = "message.saleId")
    @Mapping(target = "fee", source = "message.total")
    @Mapping(target = "serviceDate", source = "message.ocurredAt")
    AddAccountPayableItemCommand toCommand(MedicationCreatedMessage message);

}
