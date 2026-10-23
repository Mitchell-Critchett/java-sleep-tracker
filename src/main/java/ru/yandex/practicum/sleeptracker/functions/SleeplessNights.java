package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.exceptions.EmptyLogException;


import java.time.Period;
import java.util.List;
import java.util.function.Function;

public class SleeplessNights implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {

        if (sessions == null || sessions.isEmpty()) {
            throw new EmptyLogException();
        }

        int days = Period.between(sessions.getFirst().getStartSleep().toLocalDate(),
                sessions.getLast().getEndSleep().toLocalDate()).getDays();

        return new SleepAnalysisResult((long) days - sessions.stream()
                .filter(SleepingSession::isNightSession)
                .count(), "количество бессонных ночей");
    }

}
