package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.messaging;

import java.io.IOException;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sa.healntrack.account_payable_service.account_payable.application.port.in.add_account_payable_item.AddAccountPayableItem;
import com.sa.healntrack.account_payable_service.account_payable.application.port.in.create_account_payable.CreateAccountPayable;
import com.sa.healntrack.account_payable_service.account_payable.application.port.in.update_account_payable_item.UpdateAccountPayableItem;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.messaging.mapper.AccountPayableMessagingMapper;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.messaging.message.HospitalizationCreatedMessage;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.messaging.message.MedicationCreatedMessage;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.messaging.message.PatientDischargedMessage;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.messaging.message.SurgeryCreatedMessage;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.messaging.message.SurgeryUpdatedMessage;
import com.sa.healntrack.account_payable_service.common.infrastructure.exception.MessageSerializationException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AccountPayableConsumer {

    private final AccountPayableMessagingMapper mapper;
    private final CreateAccountPayable createAccountPayable;
    private final AddAccountPayableItem addAccountPayableItem;
    private final UpdateAccountPayableItem updateAccountPayableItem;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "hospitalization.created", groupId = "account-payable-service-hc")
    public void listenHospitalizationCreated(byte[] data) {
        try {
            HospitalizationCreatedMessage message = objectMapper
                    .readValue(data, HospitalizationCreatedMessage.class);
            createAccountPayable.create(mapper.toCommand(message));
        } catch (IOException e) {
            throw new MessageSerializationException("No se pudo deserializar el mensaje");
        }
    }

    @KafkaListener(topics = "hospitalization.patient-discharged", groupId = "account-payable-service-pd")
    public void listenPatientDischarged(byte[] data) {
        try {
            PatientDischargedMessage message = objectMapper
                    .readValue(data, PatientDischargedMessage.class);
            addAccountPayableItem.add(mapper.toCommand(message));
        } catch (IOException e) {
            throw new MessageSerializationException("No se pudo deserializar el mensaje");
        }
    }

    @KafkaListener(topics = "hospitalization.surgery-created", groupId = "account-payable-service-sc")
    public void listenSurgeryCreated(byte[] data) {
        try {
            SurgeryCreatedMessage message = objectMapper
                    .readValue(data, SurgeryCreatedMessage.class);
            addAccountPayableItem.add(mapper.toCommand(message));
        } catch (IOException e) {
            throw new MessageSerializationException("No se pudo deserializar el mensaje");
        }
    }

    @KafkaListener(topics = "hospitalization.surgery-updated", groupId = "account-payable-service-su")
    public void listenSurgeryUpdated(byte[] data) {
        try {
            SurgeryUpdatedMessage message = objectMapper
                    .readValue(data, SurgeryUpdatedMessage.class);
            updateAccountPayableItem.update(mapper.toCommand(message));
        } catch (IOException e) {
            throw new MessageSerializationException("No se pudo deserializar el mensaje");
        }
    }

    @KafkaListener(topics = "pharmacy.medication-created", groupId = "account-payable-service-mc")
    public void listenMedicationCreated(byte[] data) {
        try {
            MedicationCreatedMessage message = objectMapper
                    .readValue(data, MedicationCreatedMessage.class);
            addAccountPayableItem.add(mapper.toCommand(message));
        } catch (IOException e) {
            throw new MessageSerializationException("No se pudo deserializar el mensaje");
        }
    }

}
