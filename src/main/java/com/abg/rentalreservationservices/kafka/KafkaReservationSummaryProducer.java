package com.abg.rentalreservationservices.kafka;

import com.abg.rentalreservationservices.entity.ReservationSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaReservationSummaryProducer {
    private static final String TOPIC = "reservation";

    @Autowired
    private KafkaTemplate<String, ReservationSummary> kafkaTemplate;

    public void produceReservationSummary(ReservationSummary reservationSummary) {
        kafkaTemplate.send(TOPIC, reservationSummary);
        System.out.println("poduced successfully " + reservationSummary);
    }

}

