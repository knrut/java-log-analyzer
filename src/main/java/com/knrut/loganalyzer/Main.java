package com.knrut.loganalyzer;

import com.knrut.loganalyzer.generator.EventGenerator;
import com.knrut.loganalyzer.model.Event;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        List<Event> events = EventGenerator.generate(100_00);

        Path output = Path.of("data","events.txt");
        Files.createDirectories(output.getParent());

        EventGenerator.writeToFile(events, output);
    }
}