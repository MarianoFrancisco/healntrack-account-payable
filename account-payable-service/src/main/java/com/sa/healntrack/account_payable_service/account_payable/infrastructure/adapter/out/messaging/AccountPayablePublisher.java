package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.messaging;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sa.healntrack.account_payable_service.account_payable.application.port.out.messaging.PublishAccountPayableClosed;
import com.sa.healntrack.account_payable_service.account_payable.domain.AccountPayable;
import com.sa.healntrack.account_payable_service.account_payable.domain.AccountPayableItem;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.messaging.mapper.AccountPayableMessagingMapper;
import com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.messaging.message.AccountPayableClosedMessage;
import com.sa.healntrack.account_payable_service.common.infrastructure.exception.MessageSerializationException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AccountPayablePublisher implements PublishAccountPayableClosed {
    
    private final KafkaTemplate<String, byte[]> template;
    private final ObjectMapper objectMapper;
    private final AccountPayableMessagingMapper mapper;

    @Override
    public void publishClosedMessage(AccountPayable accountPayable) {
        for (AccountPayableItem item : accountPayable.getItems()) {
            try {
                AccountPayableClosedMessage message = mapper
                        .toClosedMessage(item, accountPayable.getPatientId().value());
                template.send("accountpayable.closed", objectMapper.writeValueAsBytes(message));
            } catch (JsonProcessingException e) {
                throw new MessageSerializationException("No se pudo serializar el mensaje");
            }
        }
    }

}
