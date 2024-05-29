package com.abg.rentalreservationservices.kafka;

import com.abg.rentalreservationservices.avro.ReservationSummaryAvro;
import com.abg.rentalreservationservices.encryption.EncryptionUtil;
import com.abg.rentalreservationservices.entity.ReservationSummary;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class KafkaReservationSummaryProducer {
    @Value("${topic.name}")
    private String topicName;
    @Autowired
    private KafkaTemplate<String, ReservationSummaryAvro> kafkaTemplate;


    public void send(ReservationSummaryAvro reservationSummaryAvro){
        CompletableFuture<SendResult<String, ReservationSummaryAvro>> future = kafkaTemplate.send(topicName, UUID.randomUUID().toString(),reservationSummaryAvro);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + reservationSummaryAvro +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        reservationSummaryAvro + "] due to : " + ex.getMessage());
            }
        });
    }

    public void produceReservationSummary(ReservationSummary reservationSummary) throws Exception {
        reservationSummary.encryptFields();
        ReservationSummaryAvro reservationSummaryAvro = ReservationSummaryMapper.mapToAvro(reservationSummary);
        send(reservationSummaryAvro);
    }


}


