
package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.exceptions.EmptyLogException;
import ru.yandex.practicum.sleeptracker.functions.SleeplessNights;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Проверка функции вычисления бессонных ночей
class SleeplessNightsTest {

    private final SleeplessNights function = new SleeplessNights();

    private SleepingSession session(
            int day, int startHour, int endDay, int endHour) {

        return new SleepingSession(
                LocalDateTime.of(2026, 10, day, startHour, 0),
                LocalDateTime.of(2026, 10, endDay, endHour, 0),
                null
        );
    }

//    Бросается исключение если список пуст
    @Test
    void shouldThrowExceptionWhenSessionsAreNull() {
        assertThrows(
                EmptyLogException.class,
                () -> function.apply(null)
        );
    }

//    Бросается исключение если список пуст
    @Test
    void shouldThrowExceptionWhenSessionsAreEmpty() {
        assertThrows(
                EmptyLogException.class,
                () -> function.apply(List.of())
        );
    }

//    Должно возвращать корректное кол-во бессонных ночей если сессия одна
    @Test
    void shouldCalculateSleeplessNightsForOneSession() {
        List<SleepingSession> sessions = List.of(
                session(20, 22, 23, 6)
        );

        var result = function.apply(sessions);

        // Между датами 3 дня, ночная сессия одна.
        assertEquals(2L, result.result);
    }

//    Должно возвращать корректное кол-во бессонных ночей если сессий несколько
    @Test
    void shouldCalculateSleeplessNightsForMultipleSessions() {
        List<SleepingSession> sessions = List.of(
                session(20, 22, 21, 6),
                session(23, 22, 24, 6)
        );

        var result = function.apply(sessions);

        assertEquals(2L, result.result);
    }
}
