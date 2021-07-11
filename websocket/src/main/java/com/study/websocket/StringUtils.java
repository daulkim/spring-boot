package com.study.websocket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringUtils {

    private static final String TIME_FOMATTER = "HH:mm:ss";

    public static String currentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FOMATTER);
        return LocalDateTime.now().format(formatter);
    }
}
