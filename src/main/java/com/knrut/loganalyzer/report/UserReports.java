package com.knrut.loganalyzer.report;

import com.knrut.loganalyzer.model.Event;
import com.knrut.loganalyzer.model.LoginEvent;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class UserReports {
//    Create a method that returns a Set<String> of unique userIds.
//
//    Create a method that returns a long representing the number of unique users.
//
//    Create a method that returns a Map<String, Long> with the total number of events per user.
//
//    Create a method that returns a List<String> of userIds sorted alphabetically (without duplicates).
//
//    Create a method that returns a boolean indicating whether a given userId exists in the data.
//
//    Create a method that returns a Map<String, Boolean> indicating whether a given user has ever had a LoginEvent.

    public static Set<String> getUniqueUsers(List<Event> events) {
        return events.stream()
                .map(Event::userId)
                .collect(Collectors.toSet());
    }

    public static Long countUniqueUsers(List<Event> events) {
        return (long) events.stream()
                .map(Event::userId)
                .distinct()
                .count();
    }

    public static Map<String, Long> getTotalNumberOfEventsPerUser(List<Event> events) {
        return events.stream()
                .collect(Collectors.groupingBy(Event::userId, Collectors.counting()));
    }

    public static List<String> getUniqueUsersSorted(List<Event> events) {
        return events.stream()
                .map(Event::userId)
                .distinct()
                .sorted()
                .toList();
    }

    public static boolean userExists(List<Event> events, String userId) {
        return events.stream()
                .anyMatch(u -> u.userId().equals(userId));
    }

    public static Map<String, Boolean> getUsersLoginPresence(List<Event> events) {
        return events.stream()
                .collect(Collectors.groupingBy(
                        Event::userId,
                        Collectors.reducing(
                                false,
                                e -> e instanceof LoginEvent,
                                Boolean::logicalOr
                        )
                ));
    }
}
