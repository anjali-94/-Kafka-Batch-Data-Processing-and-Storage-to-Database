package com.example.kafkamongo.kafka_mongo_consumer.repo;

import com.example.kafkamongo.kafka_mongo_consumer.model.ScoreRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScoreRecordRepository extends MongoRepository<ScoreRecord, String> {
}