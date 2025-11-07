package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.messaging;

import java.io.IOException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sa.healntrack.account_payable_service.account_payable.application.port.in.add_account_payable_item.AddAccountPayableItem;
import com.sa.healntrack.account_payable_service.account_payable.application.port.in.create_account_payable.CreateAccountPayable;
import com.sa.healntrack.account_payable_service.account_payable.application.port.in.update_account_payable_item.UpdateAccountPayableItem;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.in.messaging.mapper.AccountPayableInMessagingMapper;
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

    private final AccountPayableInMessagingMapper mapper;
    private final CreateAccountPayable createAccountPayable;
    private final AddAccountPayableItem addAccountPayableItem;
    private final UpdateAccountPayableItem updateAccountPayableItem;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "hospitalization.created")
    public void listenHospitalizationCreated(ConsumerRecord<String, byte[]> record) {
        try {
            HospitalizationCreatedMessage message = objectMapper
                    .readValue(record.value(), HospitalizationCreatedMessage.class);
            createAccountPayable.create(mapper.toCommand(message));
        } catch (IOException e) {
            throw new MessageSerializationException("No se pudo deserializar el mensaje");
        }
    }

    @KafkaListener(topics = "hospitalization.patient-discharged")
    public void listenPatientDischarged(ConsumerRecord<String, byte[]> record) {
        try {
            PatientDischargedMessage message = objectMapper
                    .readValue(record.value(), PatientDischargedMessage.class);
            addAccountPayableItem.add(mapper.toCommand(message));
        } catch (IOException e) {
            throw new MessageSerializationException("No se pudo deserializar el mensaje");
        }
    }

    @KafkaListener(topics = "hospitalization.surgery-created")
    public void listenSurgeryCreated(ConsumerRecord<String, byte[]> record) {
        try {
            SurgeryCreatedMessage message = objectMapper
                    .readValue(record.value(), SurgeryCreatedMessage.class);
            addAccountPayableItem.add(mapper.toCommand(message));
        } catch (IOException e) {
            throw new MessageSerializationException("No se pudo deserializar el mensaje");
        }
    }

    @KafkaListener(topics = "hospitalization.surgery-updated")
    public void listenSurgeryUpdated(ConsumerRecord<String, byte[]> record) {
        try {
            SurgeryUpdatedMessage message = objectMapper
                    .readValue(record.value(), SurgeryUpdatedMessage.class);
            updateAccountPayableItem.update(mapper.toCommand(message));
        } catch (IOException e) {
            throw new MessageSerializationException("No se pudo deserializar el mensaje");
        }
    }

    @KafkaListener(topics = "pharmacy.medication-created")
    public void listenMedicationCreated(ConsumerRecord<String, byte[]> record) {
        try {
            MedicationCreatedMessage message = objectMapper
                    .readValue(record.value(), MedicationCreatedMessage.class);
            addAccountPayableItem.add(mapper.toCommand(message));
        } catch (IOException e) {
            throw new MessageSerializationException("No se pudo deserializar el mensaje");
        }
    }

}
