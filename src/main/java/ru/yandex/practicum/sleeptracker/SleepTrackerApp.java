package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.functions.AverageSessionTime;
import ru.yandex.practicum.sleeptracker.functions.BadSessionsCount;
import ru.yandex.practicum.sleeptracker.functions.MinSession;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SleepTrackerApp {

    public static void main(String[] args) {

        List<Function<ArrayList<SleepingSession>, SleepAnalysisResult>> functions = new ArrayList<>();

        Function<ArrayList<SleepingSession>, SleepAnalysisResult> maxSession = new MinSession();
        Function<ArrayList<SleepingSession>, SleepAnalysisResult> minSession = new MinSession();
        Function<ArrayList<SleepingSession>, SleepAnalysisResult> avgSessionTime = new AverageSessionTime();
        Function<ArrayList<SleepingSession>, SleepAnalysisResult> badSessionsCount = new BadSessionsCount();

        functions.add(maxSession);
        functions.add(minSession);
        functions.add(avgSessionTime);
        functions.add(badSessionsCount);


    }
}