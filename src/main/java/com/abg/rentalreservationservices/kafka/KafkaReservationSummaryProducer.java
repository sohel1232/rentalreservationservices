package com.abg.rentalreservationservices.kafka;

import com.abg.rentalreservationservices.entity.ReservationSummary;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class KafkaReservationSummaryProducer {
    private static final String TOPIC = "reservation";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final SecretKey encryptionKey;

    @Autowired
    private KafkaTemplate<String, String > kafkaTemplate;

    @Autowired
    public KafkaReservationSummaryProducer(SecretKey encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    public void produceReservationSummary(ReservationSummary reservationSummary) throws Exception {
        System.out.println("HI 5");
        reservationSummary.encryptFields(encryptionKey);
        System.out.println("HI 8");
        String jsonString = objectMapper.writeValueAsString(reservationSummary);
        System.out.println("poduced successfully " + jsonString);
        kafkaTemplate.send(TOPIC, jsonString);
        System.out.println("HI 9");
    }
}

