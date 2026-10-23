package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.exceptions.EmptyLogException;
import ru.yandex.practicum.sleeptracker.functions.BadSessionsCount;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Функция подсчёта сессий с плохим качеством сна
class BadSessionsCountTest {

    private final BadSessionsCount function = new BadSessionsCount();

    private SleepingSession session(SleepQuality quality) {
        return new SleepingSession(
                LocalDateTime.of(2026, 10, 20, 22, 0),
                LocalDateTime.of(2026, 10, 21, 6, 0),
                quality
        );
    }

//    Проверка на корректный подсчёт
    @Test
    void shouldCountBadSessions() {
        List<SleepingSession> sessions = List.of(
                session(SleepQuality.BAD),
                session(SleepQuality.GOOD),
                session(SleepQuality.BAD),
                session(SleepQuality.NORMAL)
        );

        SleepAnalysisResult result = function.apply(sessions);

        assertEquals(2L, result.result);
    }

//    Возвращается 0 если нет плохих сессий
    @Test
    void shouldReturnZeroWhenNoBadSessionsExist() {
        List<SleepingSession> sessions = List.of(
                session(SleepQuality.GOOD),
                session(SleepQuality.NORMAL)
        );

        SleepAnalysisResult result = function.apply(sessions);

        assertEquals(0L, result.result);
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
