package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.exceptions.EmptyLogException;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserIdentifier implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {

        if (sessions == null || sessions.isEmpty()) {
            throw new EmptyLogException();
        }

        //Считает ночи и сохраняет их в соответствие
        Map<Integer, Long> nightsType = sessions.stream()
                .filter(SleepingSession::isNightSession)
                .collect(
                        Collectors.groupingBy(
                            this::nightIdentifier,
                            Collectors.counting()
                        )
                );

        List<Long> nightsList = Arrays.asList(
                nightsType.getOrDefault(1, 0L),
                nightsType.getOrDefault(2, 0L),
                nightsType.getOrDefault(3, 0L)
        );

        if (nightsList.get(0) > Math.max(nightsList.get(1),nightsList.get(2))) {
            return new SleepAnalysisResult("Вы сова");
        }
        if (nightsList.get(1) > Math.max(nightsList.get(0),nightsList.get(2))) {
            return new SleepAnalysisResult("Вы жаворонок");
        }

        return new SleepAnalysisResult("Вы голубь");

    }

    /*
     Вспомогательная функция
     1 - Сова
     2 - Жаворонок
     3 - Голубь
    */
    private Integer nightIdentifier(SleepingSession session) {
        LocalTime sleepStart = session.getStartSleep().toLocalTime();
        LocalTime sleepEnd = session.getEndSleep().toLocalTime();

        if (sleepStart.isAfter(LocalTime.of(23, 0))
                && sleepEnd.isAfter(LocalTime.of(9, 0)))
            return 1;

        if (sleepStart.isBefore(LocalTime.of(22, 0))
                && sleepEnd.isBefore(LocalTime.of(7,0)))
            return 2;

        else return 3;
    }
}
