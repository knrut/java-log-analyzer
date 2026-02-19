package com.knrut.loganalyzer;

import com.knrut.loganalyzer.generator.EventGenerator;
import com.knrut.loganalyzer.io.EventFileReader;
import com.knrut.loganalyzer.model.Event;
import com.knrut.loganalyzer.parser.EventParser;
import com.knrut.loganalyzer.report.BasicReports;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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
    }
}