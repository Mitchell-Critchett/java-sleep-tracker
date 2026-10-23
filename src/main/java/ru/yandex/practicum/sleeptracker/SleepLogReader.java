package ru.yandex.practicum.sleeptracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SleepLogReader {

    public static List<SleepingSession> getSessionsList(String filename) {


        try (BufferedReader bf = new BufferedReader(new FileReader(filename, StandardCharsets.UTF_8))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

            return bf.lines()
                    .map(string -> {
                        String[] arrStr = string.split(";");
                        LocalDateTime startDate = LocalDateTime.parse(arrStr[0], formatter);
                        LocalDateTime endDate = LocalDateTime.parse(arrStr[1], formatter);
                        SleepQuality sleepQuality = qualityIdentifier(arrStr[2]);
                        return new SleepingSession(startDate, endDate, sleepQuality);
                    })
                    .toList();

        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Показатель качества сна, в записи лога некорректен");
        } catch (IOException e) {
            throw new RuntimeException("Ошибка ввода данных");
        }
    }

    // Вспомогательный метод определяет качества сна
    private static SleepQuality qualityIdentifier(String str) {
        return switch (str) {
            case "GOOD" -> SleepQuality.GOOD;
            case "NORMAL" -> SleepQuality.NORMAL;
            case "BAD" -> SleepQuality.BAD;
            default -> throw new IllegalArgumentException();
        };
    }
}
