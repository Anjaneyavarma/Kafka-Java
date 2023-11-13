package com.consumer.services;

import com.consumer.model.TransactionMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Slf4j
public class KafkaConsumerService {

    @KafkaListener(topics = {"transaction-topic"}, groupId = "group-id")
    public void consumer(TransactionMessage transactionMessage){
        log.info("we recieve transaction message with id"
                + transactionMessage.getTransactionId()+
                " the status is "+ transactionMessage.getStatus());
    }

}
