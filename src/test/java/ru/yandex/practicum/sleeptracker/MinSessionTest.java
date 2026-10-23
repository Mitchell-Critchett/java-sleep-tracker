package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.exceptions.EmptyLogException;
import ru.yandex.practicum.sleeptracker.functions.MinSession;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//     Функция подсчёта минимальной продолжительности сна
class MinSessionTest {

    private final MinSession function = new MinSession();

    private SleepingSession session(int day, int startHour, int endDay, int endHour) {
        return new SleepingSession(
                LocalDateTime.of(2026, 10, day, startHour, 0),
                LocalDateTime.of(2026, 10, endDay, endHour, 0),
                null
        );
    }

    //    Проверка корректности подсчётов
    @Test
    void shouldReturnShortestSessionInMinutes() {
        List<SleepingSession> sessions = List.of(
                session(20, 22, 21, 6), // 480 минут
                session(21, 23, 22, 4), // 300 минут
                session(22, 22, 23, 4)  // 360 минут
        );

        var result = function.apply(sessions);

        assertEquals(300L, result.result);
    }

    //    Должен возвращать продолжительность одной сессии если она единственная
    @Test
    void shouldReturnDurationWhenOnlyOneSessionExists() {
        List<SleepingSession> sessions = List.of(
                session(20, 23, 21, 6)
        );

        var result = function.apply(sessions);

        assertEquals(420L, result.result);
    }

    //    Бросается исключение если список пуст или null
    @Test
    void shouldThrowExceptionWhenSessionsAreNull() {
        assertThrows(
                EmptyLogException.class,
                () -> function.apply(null)
        );
    }
}
