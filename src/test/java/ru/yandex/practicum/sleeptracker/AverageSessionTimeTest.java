
package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.functions.AverageSessionTime;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Тест функции вычисления среднего времени сна
class AverageSessionTimeTest {

    private final AverageSessionTime averageSessionTime =
            new AverageSessionTime();

    //Проверка на корректное вычисление
    @Test
    void shouldCalculateAverageSessionTime() {
        // Arrange
        SleepingSession session1 = new SleepingSession(
                LocalDateTime.of(2026, 10, 20, 22, 0),
                LocalDateTime.of(2026, 10, 21, 6, 0),
                null
        );

        SleepingSession session2 = new SleepingSession(
                LocalDateTime.of(2026, 10, 21, 22, 0),
                LocalDateTime.of(2026, 10, 22, 5, 0),
                null
        );

        List<SleepingSession> sessions = List.of(session1, session2);

        // Act
        SleepAnalysisResult result = averageSessionTime.apply(sessions);

        // Assert
        assertNotNull(result);
        assertEquals(450L, result.result);
        assertEquals(
                "Средняя продолжительность сна в минутах",
                result.text
        );
    }

    //Возвращает null если список сессий пуст
    @Test
    void shouldReturnNullWhenSessionsListIsEmpty() {
        List<SleepingSession> sessions = List.of();

        SleepAnalysisResult result = averageSessionTime.apply(sessions);

        assertNull(result);
    }

//    Должен возвращать продолжительность одной сессии если она единственная
    @Test
    void shouldReturnSessionDurationWhenListContainsOneSession() {
        SleepingSession session = new SleepingSession(
                LocalDateTime.of(2026, 10, 22, 23, 0),
                LocalDateTime.of(2026, 10, 23, 6, 0),
                null
        );

        List<SleepingSession> sessions = List.of(session);

        SleepAnalysisResult result = averageSessionTime.apply(sessions);

        assertNotNull(result);
        assertEquals(420L, result.result);
    }
}
