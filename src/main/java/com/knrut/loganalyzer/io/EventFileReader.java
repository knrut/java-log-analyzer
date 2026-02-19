package com.knrut.loganalyzer.io;

import com.knrut.loganalyzer.model.Event;
import com.knrut.loganalyzer.parser.Parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class EventFileReader {
    private final Parser<Event> parser;

    public EventFileReader(Parser<Event> parser) {
        this.parser = parser;
    }

    public List<Event> read(Path path) throws IOException {
        try (var lines = Files.lines(path)) {
            return lines
                    .filter(l -> !l.isBlank())
                    .map(parser::parse)
                    .toList();
        }
    }
}
