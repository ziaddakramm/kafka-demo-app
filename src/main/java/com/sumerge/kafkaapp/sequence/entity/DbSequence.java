package com.sumerge.kafkaapp.sequence.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "db_sequence")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DbSequence {

    private static final String SEQUENCE_NAME="user_sequence";

    @Id
    private String id;
    private int seq;
}
