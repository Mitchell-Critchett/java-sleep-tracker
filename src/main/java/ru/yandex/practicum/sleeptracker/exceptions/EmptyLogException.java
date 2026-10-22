package ru.yandex.practicum.sleeptracker.exceptions;

public class EmptyLogException extends RuntimeException {
    public EmptyLogException() {
        super("У вас не обнаружено не одной записи о сне");
    }
}
