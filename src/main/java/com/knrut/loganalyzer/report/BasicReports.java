package com.knrut.loganalyzer.report;

import com.knrut.loganalyzer.model.ErrorEvent;
import com.knrut.loganalyzer.model.Event;
import com.knrut.loganalyzer.model.PurchaseEvent;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class BasicReports {

    public static Set<String> uniqueUsers(List<Event> events) {
        return events.stream()
                .map(Event::userId)
                .collect(Collectors.toSet());
    }

    public static Map<String, Long> eventsPerUser(List<Event> events) {
        return events.stream()
                .collect(Collectors.groupingBy(Event::userId, Collectors.counting()));
    }

    public static BigDecimal totalRevenue(List<Event> events) {
        return events.stream()
                .filter(PurchaseEvent.class::isInstance)
                .map(PurchaseEvent.class::cast)
                .map(PurchaseEvent::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static Map<Integer, Long> errorsByCode(List<Event> events) {
        return events.stream()
                .filter(ErrorEvent.class::isInstance)
                .map(ErrorEvent.class::cast)
                .collect(Collectors.groupingBy(ErrorEvent::errorCode, Collectors.counting()));
    }

    public static List<Map.Entry<String, Long>> topUsersByEvents(List<Event> events, int topN) {
        return eventsPerUser(events).entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(topN)
                .toList();
    }
}
