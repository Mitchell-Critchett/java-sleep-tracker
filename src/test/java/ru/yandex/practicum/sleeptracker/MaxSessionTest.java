package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.exceptions.EmptyLogException;
import ru.yandex.practicum.sleeptracker.functions.MaxSession;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//     Функция подсчёта максимальной продолжительности сна
class MaxSessionTest {

    private final MaxSession function = new MaxSession();

    private SleepingSession session(int day, int startHour, int endDay, int endHour) {
        return new SleepingSession(
                LocalDateTime.of(2026, 10, day, startHour, 0),
                LocalDateTime.of(2026, 10, endDay, endHour, 0),
                null
        );
    }

    //    Проверка корректности подсчётов
    @Test
    void shouldReturnLongestSessionInMinutes() {
        List<SleepingSession> sessions = List.of(
                session(20, 22, 21, 4),  // 360 минут
                session(21, 22, 22, 6),  // 480 минут
                session(22, 22, 23, 3)   // 300 минут
        );

        var result = function.apply(sessions);

        assertEquals(480L, result.result);
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
    void shouldThrowExceptionWhenSessionsAreEmpty() {
        assertThrows(
                EmptyLogException.class,
                () -> function.apply(List.of())
        );
    }
}

