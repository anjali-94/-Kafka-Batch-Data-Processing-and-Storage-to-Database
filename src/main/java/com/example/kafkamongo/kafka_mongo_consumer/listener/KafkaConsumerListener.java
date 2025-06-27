package com.example.kafkamongo.kafka_mongo_consumer.listener;

import com.example.kafkamongo.kafka_mongo_consumer.model.ScoreRecord;
import com.example.kafkamongo.kafka_mongo_consumer.repo.ScoreRecordRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaConsumerListener {

    @Autowired
    private ScoreRecordRepository repo;

    private final ObjectMapper mapper = new ObjectMapper();
    private final List<ScoreRecord> buffer = new ArrayList<>();
    private final int BATCH_SIZE = 5;

    @KafkaListener(topics = "test-topic", groupId = "my-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(ConsumerRecord<String, String> record) {
        try {
            System.out.println("Received from Partition: " + record.partition());

            ScoreRecord data = mapper.readValue(record.value(), ScoreRecord.class);
            buffer.add(data);

            if (buffer.size() >= BATCH_SIZE) {
                repo.saveAll(buffer);
                System.out.println("Inserted batch of 5 to MongoDB");
                buffer.clear();
            }

        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            System.err.println("JSON parse error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
}
