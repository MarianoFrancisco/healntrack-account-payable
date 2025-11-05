package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.event;

import java.io.IOException;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sa.healntrack.account_payable_service.account_payable.application.port.in.add_account_payable_item.AddAccountPayableItem;
import com.sa.healntrack.account_payable_service.account_payable.application.port.in.create_account_payable.CreateAccountPayable;
import com.sa.healntrack.account_payable_service.account_payable.application.port.in.remove_account_payable_item.RemoveAccountPayableItem;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.event.mapper.AccountPayableEventMapper;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.event.message.HospitalizationCreatedMessage;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.event.message.PatientDischargedMessage;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.event.message.SurgeryCreatedMessage;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.event.message.SurgeryUpdatedMessage;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AccountPayableConsumer {

    private final AccountPayableEventMapper mapper;
    private final CreateAccountPayable createAccountPayable;
    private final AddAccountPayableItem addAccountPayableItem;
    private final RemoveAccountPayableItem removeAccountPayableItem;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "hospitalization.created", groupId = "account-payable-service-hc")
    public void listenHospitalizationCreated(byte[] data) {
        try {
            HospitalizationCreatedMessage message = objectMapper
                    .readValue(data, HospitalizationCreatedMessage.class);
            createAccountPayable.create(mapper.toCommand(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "hospitalization.patient-discharged", groupId = "account-payable-service-pd")
    public void listenPatientDischarged(byte[] data) {
        try {
            PatientDischargedMessage message = objectMapper
                    .readValue(data, PatientDischargedMessage.class);
            addAccountPayableItem.add(mapper.toCommand(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "hospitalization.surgery-created", groupId = "account-payable-service-sc")
    public void listenSurgeryCreated(byte[] data) {
        try {
            SurgeryCreatedMessage message = objectMapper
                    .readValue(data, SurgeryCreatedMessage.class);
            addAccountPayableItem.add(mapper.toCommand(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "hospitalization.surgery-updated", groupId = "account-payable-service-su")
    public void listenSurgeryUpdated(byte[] data) {
        try {
            SurgeryUpdatedMessage message = objectMapper
                    .readValue(data, SurgeryUpdatedMessage.class);
            removeAccountPayableItem.remove(mapper.toRCommand(message));
            addAccountPayableItem.add(mapper.toCommand(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
