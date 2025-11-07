package com.sa.healntrack.account_payable_service.account_payable.infrastructure.adapter.out.messaging;

import java.util.List;
import java.util.Map;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sa.healntrack.account_payable_service.account_payable.application.port.out.messaging.PublishAccountPayableClosed;
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
    
    private static final Map<String, String> TOPICS = Map.of(
        "MED", "accountpayable.sale.completed"
    );

    @Override
    public void publishClosedMessage(List<AccountPayableItem> items) {
        for (AccountPayableItem item : items) {
            try {
                String topic = TOPICS.getOrDefault(
                        item.getServiceType().getName().value(),
                        "accountpayable.closed");
                AccountPayableClosedMessage message = mapper.toClosedMessage(item);
                template.send(topic, objectMapper.writeValueAsBytes(message));
            } catch (JsonProcessingException e) {
                throw new MessageSerializationException("No se pudo serializar el mensaje");
            }
        }
    }

}
