package com.producer.kafka.services;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.producer.kafka.models.TransactionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class KafkaProducerService {

    private final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

    @Autowired
    KafkaTemplate<UUID, TransactionMessage> kafkaTemplate;

    public void send(String topicName, UUID key, TransactionMessage message){
        var future = kafkaTemplate.send(topicName, key, message);
        future.whenComplete((sendResult, exception) -> {
            if(exception!=null){
                future.completeExceptionally(exception);
                System.out.println(exception);
            }else{
                future.complete(sendResult);
            }

            logger.info("the id is : "+ message.getTransactionId() +"Transaction Statuse to kafka Tokpic:"+ message.getStatus());

        });

    }

}
