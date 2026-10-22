package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.exceptions.EmptyLogException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Function;

public class MinSession implements Function<ArrayList<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(ArrayList<SleepingSession> sessions) {
        if (sessions == null || sessions.isEmpty()) {
            throw new EmptyLogException();
        }

        return new SleepAnalysisResult(Collections.min(sessions, Comparator.comparingLong(SleepingSession::getDuration))
                .getDuration() / 60, "Продолжительность самого короткого сна в минутах");
    }
}
