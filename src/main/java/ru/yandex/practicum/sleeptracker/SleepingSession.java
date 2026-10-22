package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.time.LocalDateTime;

public class SleepingSession {

    private LocalDateTime startSleep;

    private LocalDateTime endSleep;

    private final SleepQuality sleepQuality;

    private final Duration duration;

    public SleepingSession(LocalDateTime startSleep, LocalDateTime endSleep, SleepQuality sleepQuality) {
        this.startSleep = startSleep;
        this.endSleep = endSleep;
        this.sleepQuality = sleepQuality;
        this.duration = Duration.between(startSleep, endSleep);
    }

    public Long getDuration() {
        return duration.getSeconds();
    }

    public SleepQuality getSleepQuality() {
        return sleepQuality;
    }
}
