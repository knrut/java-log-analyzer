package com.knrut.loganalyzer.parser;

import com.knrut.loganalyzer.model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public final class EventParser implements Parser<Event> {

    @Override
    public Event parse(String line) {
        String[] parts = line.split(";");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid line (too few fields): " + line);
        }

        LocalDateTime ts = LocalDateTime.parse(parts[0]);
        String userId = parts[1];
        String type = parts[2];

        Map<String, String> meta = parseMeta(parts, 3);

        return switch (type) {
            case "LOGIN" -> new LoginEvent(ts, userId, required(meta, "ip"));
            case "LOGOUT" -> new LogoutEvent(ts, userId);
            case "PURCHASE" -> new PurchaseEvent(
                    ts,
                    userId,
                    new BigDecimal(required(meta, "amount")),
                    required(meta, "item")
            );
            case "ERROR" -> new ErrorEvent(ts, userId, Integer.parseInt(required(meta, "code")));
            default -> throw new IllegalArgumentException("Unknown event type: " + type + " in line: " + line);
        };
    }

    private static Map<String, String> parseMeta(String[] parts, int startIdx) {
        Map<String, String> meta = new HashMap<>();
        for (int i = startIdx; i < parts.length; i++) {
            String p = parts[i];
            int eq = p.indexOf('=');
            if (eq <= 0 || eq == p.length() - 1) continue;
            meta.put(p.substring(0, eq), p.substring(eq + 1));
        }
        return meta;
    }

    private static String required(Map<String, String> meta, String key) {
        String value = meta.get(key);
        if (value == null || value.isBlank()) throw new IllegalArgumentException("Missing meta: " + key);
        return value;
    }
}
