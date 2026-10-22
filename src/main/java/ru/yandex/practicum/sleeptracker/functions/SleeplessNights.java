package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.exceptions.EmptyLogException;

import java.time.Duration;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.function.Function;

import static java.time.Period.between;

public class SleeplessNights implements Function<ArrayList<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(ArrayList<SleepingSession> sessions) {

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
