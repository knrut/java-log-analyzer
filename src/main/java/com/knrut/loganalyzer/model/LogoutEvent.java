package com.knrut.loganalyzer.model;

import java.time.LocalDateTime;

public record LogoutEvent(
        LocalDateTime timestamp,
        String userId
) implements Event {}
