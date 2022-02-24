package ru.itmo.banks.observer;

public interface Subject {
    void Attach(Observer observer);
    void Detach(Observer observer);
    void Notify();
}
