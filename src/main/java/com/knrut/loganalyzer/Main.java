package com.knrut.loganalyzer;

import com.knrut.loganalyzer.generator.EventGenerator;
import com.knrut.loganalyzer.io.EventFileReader;
import com.knrut.loganalyzer.model.Event;
import com.knrut.loganalyzer.parser.EventParser;
import com.knrut.loganalyzer.report.BasicReports;
import com.knrut.loganalyzer.report.ErrorReports;
import com.knrut.loganalyzer.report.RevenueReports;
import com.knrut.loganalyzer.report.UserReports;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws Exception {
//
//        List<Event> events = EventGenerator.generate(10_000);
//
//        Path output = Path.of("data","events.txt");
//        Files.createDirectories(output.getParent());
//
//        EventGenerator.writeToFile(events, output);

        Path input = Path.of("data", "events.txt");

        EventFileReader reader = new EventFileReader(new EventParser());
        var events = reader.read(input);

        System.out.println("Users: " + BasicReports.uniqueUsers(events).size());
        System.out.println("Revenue: " + BasicReports.totalRevenue(events));
        System.out.println("Top 5: " + BasicReports.topUsersByEvents(events, 5));
        System.out.println("Errors: " + BasicReports.errorsByCode(events));


        System.out.println("------------------------------------- Revenue Reports ------------------------------------");
        System.out.println("Total Purchases: " + RevenueReports.countTotalPurchaseEvents(events));
        System.out.println("Sum Total Purchases: " + RevenueReports.sumTotalPurchaseEvents(events));
        System.out.println("Highest Purchase: " + RevenueReports.getHighestPurchase(events).get());
        System.out.println("Lowest Purchase: " + RevenueReports.getLowestPurchase(events).get());
        System.out.println("All Purchases Amount Sorted: " + RevenueReports.getAllPurchaseAmountsSorted(events));
        System.out.println("Unique Sorted Items: " + RevenueReports.getUniquePurchasedItems(events));

        System.out.println("------------------------------------- Error Reports ------------------------------------");
        System.out.println("Count Error Events: " + ErrorReports.countErrorEvents(events));
        System.out.println("Unique Error Codes: " + ErrorReports.uniqueErrorCodes(events));
        System.out.println("Error Counts by Code: " + ErrorReports.errorsCountByCode(events));
        System.out.println("Most Frequent Error Code: " + ErrorReports.mostFrequentErrorCode(events));
        System.out.println("Has Entry Code: " + ErrorReports.hasEntryCode(events, 200));

    }
}