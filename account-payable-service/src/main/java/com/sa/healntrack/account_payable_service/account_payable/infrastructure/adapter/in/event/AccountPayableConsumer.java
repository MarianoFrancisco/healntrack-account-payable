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

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AccountPayableConsumer {

    private final AccountPayableEventMapper mapper;
    private final CreateAccountPayable createAccountPayable;
    private final AddAccountPayableItem addAccountPayableItem;
    private final RemoveAccountPayableItem removeAccountPayableItem;

    @KafkaListener(topics = "hospitalization.created", groupId = "account-payable-service")
    public void listenHospitalizationCreated(byte[] data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            HospitalizationCreatedMessage message = objectMapper
                    .readValue(data, HospitalizationCreatedMessage.class);
            createAccountPayable.create(mapper.toCommand(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    

}
