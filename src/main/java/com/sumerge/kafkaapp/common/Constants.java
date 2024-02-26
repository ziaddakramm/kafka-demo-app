package com.sumerge.kafkaapp.common;

import java.time.format.DateTimeFormatter;


public final class Constants {

    public static final String TOPIC_NAME = "employees";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_DATE;

    private Constants() {
    }

}
