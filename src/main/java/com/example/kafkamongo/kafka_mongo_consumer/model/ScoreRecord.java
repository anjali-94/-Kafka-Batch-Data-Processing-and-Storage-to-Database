package com.example.kafkamongo.kafka_mongo_consumer.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("kafka_data")
public class ScoreRecord {

    @Id
    private String id;

    private String name;
    private int score;

    // Default constructor (required for Spring Data & Jackson)
    public ScoreRecord() {
    }

    // Parameterized constructor (optional, useful for testing)
    public ScoreRecord(String name, int score) {
        this.name = name;
        this.score = score;
    }

    // Full constructor (optional)
    public ScoreRecord(String id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    // Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // Optional: toString (useful for logging)
    @Override
    public String toString() {
        return "ScoreRecord{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
