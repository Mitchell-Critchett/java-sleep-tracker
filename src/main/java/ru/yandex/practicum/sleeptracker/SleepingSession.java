package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SleepingSession {

    private final LocalDateTime startSleep;

    private final LocalDateTime endSleep;

    private final SleepQuality sleepQuality;

    private final Duration duration;

    public SleepingSession(LocalDateTime startSleep, LocalDateTime endSleep, SleepQuality sleepQuality) {
        this.startSleep = startSleep;
        this.endSleep = endSleep;
        this.sleepQuality = sleepQuality;
        this.duration = Duration.between(startSleep, endSleep);
    }

    public LocalDateTime getStartSleep() {
        return startSleep;
    }

    public LocalDateTime getEndSleep() {
        return endSleep;
    }

    public Long getDuration() {
        return duration.getSeconds();
    }

    public SleepQuality getSleepQuality() {
        return sleepQuality;
    }

    //Вспомогательная функция для проверки на то что промежуток времени это ночной сон
    public boolean isNightSession() {

        LocalTime start = startSleep.toLocalTime();
        LocalTime end = endSleep.toLocalTime();
        LocalTime nightEnd = LocalTime.of(6, 0);

        //Если начинается до 6:00, то это точно ночной сон
        if (start.isBefore(nightEnd)) {
            return true;
        }

        //Если после 6:00, но и после полуночи (заходит на следующий день), то это тоже ночной сон
        if (!start.isBefore(nightEnd) && end.isAfter(LocalTime.MIDNIGHT)) {
            return true;
        }

        return false;
    }
}
