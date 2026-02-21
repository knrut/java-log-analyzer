package com.knrut.loganalyzer.report;

import com.knrut.loganalyzer.model.Event;
import com.knrut.loganalyzer.model.PurchaseEvent;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class RevenueReports {
//    Create a method that returns a long representing the total number of PurchaseEvents.
//
//    Create a method that returns a BigDecimal representing the sum of all purchases (total revenue).
//
//    Create a method that returns an Optional<BigDecimal> representing the highest purchase amount.
//
//    Create a method that returns an Optional<BigDecimal> representing the lowest purchase amount.
//
//    Create a method that returns a List<BigDecimal> of all purchase amounts (only PurchaseEvents), sorted in ascending order.
//
//    Create a method that returns a Set<String> of unique items that have ever been purchased.

    public static long countTotalPurchaseEvents(List<Event> events) {
        return events.stream()
                .filter(e -> e instanceof PurchaseEvent)
                .count();
    }

    public static BigDecimal sumTotalPurchaseEvents(List<Event> events) {
         return events.stream()
                 .filter(PurchaseEvent.class::isInstance)
                 .map(PurchaseEvent.class::cast)
                 .map(PurchaseEvent::amount)
                 .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static Optional<BigDecimal> getHighestPurchase(List<Event> events) {
        return events.stream()
                .filter(PurchaseEvent.class::isInstance)
                .map(PurchaseEvent.class::cast)
                .map(PurchaseEvent::amount)
                .max(BigDecimal::compareTo);
    }

    public static Optional<BigDecimal> getLowestPurchase(List<Event> events) {
        return events.stream()
                .filter(PurchaseEvent.class::isInstance)
                .map(PurchaseEvent.class::cast)
                .map(PurchaseEvent::amount)
                .min(BigDecimal::compareTo);
    }

    public static List<BigDecimal> getAllPurchaseAmountsSorted(List<Event> events) {
        return events.stream()
                .filter(PurchaseEvent.class::isInstance)
                .map(PurchaseEvent.class::cast)
                .map(PurchaseEvent::amount)
                .sorted()
                .toList();
    }

    public static Set<String> getUniquePurchasedItems(List<Event> events) {
        return events.stream()
                .filter(PurchaseEvent.class::isInstance)
                .map(PurchaseEvent.class::cast)
                .map(PurchaseEvent::item)
                .collect(Collectors.toSet());
    }
}
