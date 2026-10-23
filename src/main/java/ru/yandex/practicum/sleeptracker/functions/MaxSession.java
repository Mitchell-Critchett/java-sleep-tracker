package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.exceptions.EmptyLogException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class MaxSession implements Function<List<SleepingSession>, SleepAnalysisResult> {

    public MaxSession() {
    }

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {

        if (sessions == null || sessions.isEmpty()){
            throw new EmptyLogException();
        }

        return new SleepAnalysisResult(Collections.max(sessions, Comparator.comparingLong(SleepingSession::getDuration))
                .getDuration() / 60, "Время самого продолжительного сна в минутах");
    }
}
