package ru.yandex.practicum.sleeptracker;

public class SleepAnalysisResult {

    public String text;
    public Long result;

    public SleepAnalysisResult(Long result, String text) {
        this.text = text;
        this.result = result;
    }

    public SleepAnalysisResult(String text) {}

    @Override
    public String toString() {

        if (result != null) {
            return "Результаты анализа " +
                    "название анализа: " + text + '\'' +
                    ", результат " + result;
        } else {
            return "Результаты анализа: " + text + '\'';
        }
    }
}
