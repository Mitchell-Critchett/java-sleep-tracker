package ru.yandex.practicum.sleeptracker;

public class SleepAnalysisResult {

    public String text;
    public Long result;

    public SleepAnalysisResult(Long result, String text) {
        this.text = text;
        this.result = result;
    }

    @Override
    public String toString() {
        return "Результаты анализа " +
                "название анализа: " + text + '\'' +
                ", результат " + result;
    }
}
