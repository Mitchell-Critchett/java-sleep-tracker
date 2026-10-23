
package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.functions.UserIdentifier;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Проверка функции определения типа пользователя
class UserIdentifierTest {

    private final UserIdentifier function = new UserIdentifier();

    private SleepingSession session(
            int day, int startHour, int startMinute,
            int endDay, int endHour) {

        return new SleepingSession(
                LocalDateTime.of(
                        2026, 10, day, startHour, startMinute
                ),
                LocalDateTime.of(
                        2026, 10, endDay, endHour, 0
                ),
                null
        );
    }

    //    Проверка корректности определения совы
    @Test
    void shouldIdentifyOwl() {
        List<SleepingSession> sessions = List.of(
                session(20, 23, 30, 21, 10),
                session(21, 23, 30, 22, 10),
                session(22, 23, 30, 23, 10),
                session(23, 21, 0, 24, 5)
        );

        var result = function.apply(sessions);

        assertEquals("Вы сова", result.text);
    }

    //    Проверка корректности определения жаворонка
    @Test
    void shouldIdentifyEarlyBird() {
        List<SleepingSession> sessions = List.of(
                session(20, 21, 0, 21, 5),
                session(21, 21, 0, 22, 5),
                session(22, 21, 0, 23, 5),
                session(23, 23, 30, 24, 10)
        );

        var result = function.apply(sessions);

        assertEquals("Вы жаворонок", result.text);
    }

    //    Проверка корректности определения голубя
    @Test
    void shouldIdentifyPigeonWhenOwlAndEarlyBirdCountsAreEqual() {
        List<SleepingSession> sessions = List.of(
                session(20, 23, 30, 21, 10),
                session(21, 21, 0, 22, 5),
                session(22, 22, 30, 23, 8)
        );

        var result = function.apply(sessions);

        assertEquals("Вы голубь", result.text);
    }
}
