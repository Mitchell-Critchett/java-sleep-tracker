package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.ArrayList;
import java.util.function.Function;

public class AverageSessionTime implements Function<ArrayList<SleepingSession>, SleepAnalysisResult> {

    public AverageSessionTime() {
    }

    @Override
    public SleepAnalysisResult apply(ArrayList<SleepingSession> sessions) {
        if (sessions == null || sessions.isEmpty()) {
            return null;
        }

        return new SleepAnalysisResult((long) sessions.stream()
                .mapToLong(SleepingSession::getDuration)
                .average()
                .orElse(0.0) / 60, "Средняя продолжительность сна в минутах");
    }
}
