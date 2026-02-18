package com.knrut.loganalyzer.generator;

import com.knrut.loganalyzer.model.Event;
import com.knrut.loganalyzer.model.EventType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public final class EventGenerator {
    private static final Random RANDOM = new Random();
    private static final List<EventType> TYPES = List.of(EventType.values());

    public static List<Event> generate(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> new Event(
                                LocalDateTime.now().minusMinutes(RANDOM.nextInt(1000)),
                                "user" + RANDOM.nextInt(100),
                                TYPES.get(RANDOM.nextInt(TYPES.size())),
                                RANDOM.nextDouble() * 100
                        ))
                .toList();
    }

    public static void writeToFile(List<Event> events, Path path) throws IOException {
        List<String> lines = events.stream()
                .map(EventGenerator::format)
                .toList();

        Files.write(path, lines);
    }

    public static String format(Event event) {
        return event.getTimestamp() + ";" +
                event.getUserId() + ";" +
                event.getType() + ";" +
                event.getValue();
    }
}
