package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.functions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SleepTrackerApp {

    public static void main(String[] args) {

            try {
                ArrayList<Function<List<SleepingSession>, SleepAnalysisResult>> functions = new ArrayList<>();

                Function<List<SleepingSession>, SleepAnalysisResult> maxSession = new MaxSession();
                Function<List<SleepingSession>, SleepAnalysisResult> minSession = new MinSession();
                Function<List<SleepingSession>, SleepAnalysisResult> avgSessionTime = new AverageSessionTime();
                Function<List<SleepingSession>, SleepAnalysisResult> badSessionsCount = new BadSessionsCount();
                Function<List<SleepingSession>, SleepAnalysisResult> sleeplessNightsCount = new SleeplessNights();
                Function<List<SleepingSession>, SleepAnalysisResult> userIdentifier = new UserIdentifier();

                functions.add(maxSession);
                functions.add(minSession);
                functions.add(avgSessionTime);
                functions.add(badSessionsCount);
                functions.add(sleeplessNightsCount);
                functions.add(userIdentifier);

                List<SleepingSession> sessions = SleepLogReader.getSessionsList("src/main/resources/sleep_log.txt");


                functions.stream()
                        .map(function -> function.apply(sessions))
                        .forEach(System.out::println);

            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
    }
}