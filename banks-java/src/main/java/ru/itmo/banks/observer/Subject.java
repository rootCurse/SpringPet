package ru.itmo.banks.observer;

public interface Subject {
    void attach(Observer observer);

    void detach(Observer observer);

    void notifyForObserver();
}
