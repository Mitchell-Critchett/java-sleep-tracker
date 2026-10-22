package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.exceptions.EmptyLogException;

import java.util.ArrayList;
import java.util.function.Function;

public class BadSessionsCount implements Function<ArrayList<SleepingSession>, SleepAnalysisResult> {

    public BadSessionsCount() {
    }

    @Override
    public SleepAnalysisResult apply(ArrayList<SleepingSession> sessions) {
        if (sessions == null || sessions.isEmpty()) {
            throw new EmptyLogException();
        }

        return new SleepAnalysisResult(sessions.stream()
                .filter(session -> session.getSleepQuality() == SleepQuality.BAD)
                .count(),
                "Количество сессий с низким качеством сна");
    }
}
