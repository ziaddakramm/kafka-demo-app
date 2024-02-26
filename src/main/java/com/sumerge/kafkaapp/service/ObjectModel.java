package com.sumerge.kafkaapp.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;


import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObjectModel implements Serializable {
    @Id
    private String id;
}
