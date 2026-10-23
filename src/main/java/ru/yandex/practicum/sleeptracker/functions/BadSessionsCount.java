package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.exceptions.EmptyLogException;

import java.util.List;
import java.util.function.Function;

public class BadSessionsCount implements Function<List<SleepingSession>, SleepAnalysisResult> {

    public BadSessionsCount() {
    }

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        if (sessions == null || sessions.isEmpty()) {
            throw new EmptyLogException();
        }

        return new SleepAnalysisResult(sessions.stream()
                .filter(session -> session.getSleepQuality() == SleepQuality.BAD)
                .count(),
                "Количество сессий с низким качеством сна");
    }
}
