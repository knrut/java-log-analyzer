package com.knrut.loganalyzer.generator;

import com.knrut.loganalyzer.model.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public final class EventGenerator {

    private static final Random RANDOM = new Random();
    private static final List<String> ITEMS = List.of("BOOK", "COFFEE", "MOUSE", "SSD", "COURSE", "TEA");
    private static final List<String> IPS = List.of("10.0.0.5", "10.0.0.8", "192.168.0.10", "172.16.0.2");

    public static List<Event> generate(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> randomEvent())
                .toList();
    }

    public static void writeToFile(List<Event> events, Path path) throws IOException {
        Files.createDirectories(path.getParent());
        List<String> lines = events.stream()
                .map(EventGenerator::format)
                .toList();
        Files.write(path, lines);
    }

    private static Event randomEvent() {
        LocalDateTime eventTime = LocalDateTime.now().minusMinutes(RANDOM.nextInt(10_000));
        String userId = "user" + RANDOM.nextInt(100);

        int pick = RANDOM.nextInt(4);
        return switch (pick) {
            case 0 -> new LoginEvent(eventTime, userId, IPS.get(RANDOM.nextInt(IPS.size())));
            case 1 -> new LogoutEvent(eventTime, userId);
            case 2 -> new PurchaseEvent(eventTime, userId, randomAmount(),ITEMS.get(RANDOM.nextInt(ITEMS.size())));
            default -> new ErrorEvent(eventTime, userId, randomErrorCode());
        };
    }

    private static int randomErrorCode() {
        int[] codes = {400, 401, 403, 404, 408, 429, 500, 502, 503};
        return codes[RANDOM.nextInt(codes.length)];
    }

    private static BigDecimal randomAmount() {
        double raw = 5 + (RANDOM.nextDouble() * 200);
        return BigDecimal.valueOf(raw).setScale(2, RoundingMode.HALF_UP);
    }

    private static String format(Event event) {
        if (event instanceof LoginEvent le) {
            return le.timestamp() + ";" + le.userId() + ";LOGIN;ip=" + le.ipAddress();
        }
        if (event instanceof LogoutEvent le) {
            return le.timestamp() + ";" + le.userId() + ";LOGOUT";
        }
        if (event instanceof PurchaseEvent pe) {
            return pe.timestamp() + ";" + pe.userId() + ";PURCHASE;amount=" + pe.amount() + ";item=" + pe.item();
        }
        if (event instanceof ErrorEvent ee) {
            return ee.timestamp() + ";" + ee.userId() + ";ERROR;code=" + ee.errorCode();
        }
        throw new IllegalStateException("Unknown Event Type: " + event.getClass());
    }
}
