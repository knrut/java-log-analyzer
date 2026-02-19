package com.knrut.loganalyzer.model;

import java.time.LocalDateTime;

public record LoginEvent(
        LocalDateTime timestamp,
        String userId,
        String ipAddress
) implements Event {}
