package com.knrut.loganalyzer.report;

import com.knrut.loganalyzer.model.ErrorEvent;
import com.knrut.loganalyzer.model.Event;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ErrorReports {
//    Create a method that returns a long representing the total number of ErrorEvents.
//    Create a method that returns a Set<Integer> of unique errorCodes.
//    Create a method that returns a Map<Integer, Long> representing the number of errors per code.
//    Create a method that returns an Optional<Integer> representing the error code that occurred most frequently.
//    Create a method that returns a boolean indicating whether an error with a specific errorCode occurred.
    public static long countErrorEvents(List<Event> events) {
        return events.stream()
                .filter(ErrorEvent.class::isInstance)
                .count();
    }

    public static Set<Integer> uniqueErrorCodes(List<Event> events) {
        return events.stream()
                .filter(ErrorEvent.class::isInstance)
                .map(ErrorEvent.class::cast)
                .map(ErrorEvent::errorCode)
                .collect(Collectors.toSet());
    }

    public static Map<Integer, Long> errorsCountByCode(List<Event> events) {
        return events.stream()
                .filter(ErrorEvent.class::isInstance)
                .map(ErrorEvent.class::cast)
                .collect(Collectors.groupingBy(ErrorEvent::errorCode, Collectors.counting()));
    }

    public static Optional<Integer> mostFrequentErrorCode(List<Event> events) {
        return errorsCountByCode(events).entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }

    public static boolean hasEntryCode(List<Event> events, int errorCode) {
        return events.stream()
                .filter(ErrorEvent.class::isInstance)
                .map(ErrorEvent.class::cast)
                .anyMatch(e -> e.errorCode() == errorCode);
    }
}
