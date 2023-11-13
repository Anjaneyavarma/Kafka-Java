package com.producer.kafka.controller;


import com.producer.kafka.models.TransactionMessage;
import com.producer.kafka.services.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class kafkaProducerController {

    @Autowired
    KafkaProducerService kafkaProducerService;

    @PostMapping("/event")
    public ResponseEntity<String> event(@RequestBody TransactionMessage transactionMessage){
        UUID uuid = UUID.randomUUID();
        log.info("we recieved the transaction with the key"+uuid);
        kafkaProducerService.send("transaction-topic", uuid, transactionMessage);
        return ResponseEntity.ok("send");
    }
}
