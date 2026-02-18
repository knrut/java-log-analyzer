package com.knrut.loganalyzer.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Event {
    private final LocalDateTime timestamp;
    private final String userId;
    private final EventType type;
    private final double value;

    public Event(LocalDateTime timestamp, String userId, EventType type, double value) {
        this.timestamp = timestamp;
        this.userId = userId;
        this.type = type;
        this.value = value;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public EventType getType() {
        return type;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Event event)) return false;
        return Double.compare(value, event.value) == 0 && Objects.equals(timestamp, event.timestamp) && Objects.equals(userId, event.userId) && type == event.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, userId, type, value);
    }

    @Override
    public String toString() {
        return "Event{" +
                "timestamp=" + timestamp +
                ", userId='" + userId + '\'' +
                ", type=" + type +
                ", value=" + value +
                '}';
    }
}
