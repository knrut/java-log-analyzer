package com.knrut.loganalyzer.model;

import java.time.LocalDateTime;

public record ErrorEvent(
        LocalDateTime timestamp,
        String userId,
        int errorCode
) implements Event {}
