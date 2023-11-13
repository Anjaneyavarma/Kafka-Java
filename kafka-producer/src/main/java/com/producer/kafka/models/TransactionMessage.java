package com.producer.kafka.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionMessage {

    private Long transactionId;
    private Event event;
    private Double amount;
    private Status status;

    public enum Event{
        DEBITED, DEPOSIT
    }

    public enum Status{
        SUBMIT, STARTED, FINISHED, TERMINATED
    }
}
