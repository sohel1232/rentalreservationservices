package consumer;

import com.abg.rentalreservationservices.avro.ReservationSummaryAvro;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaAvroConsumer {

    @KafkaListener(topics = "${topic.name}")
    public void read(ConsumerRecord<String, ReservationSummaryAvro> reservationSummaryAvroConsumerRecord) {
        String key = reservationSummaryAvroConsumerRecord.key();
        ReservationSummaryAvro employee = reservationSummaryAvroConsumerRecord.value();
        log.info("Avro message received for key : " + key + " value : " + employee.toString());

    }
}
