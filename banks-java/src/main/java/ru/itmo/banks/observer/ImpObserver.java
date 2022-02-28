package ru.itmo.banks.observer;

public class ImpObserver implements Observer {
    private final String _name;

    public ImpObserver(String name) {
        _name = name;
    }

    @Override
    public void update(Subject iSubject) {
        System.out.println("Bank change interest");
    }

    public String get_name() {
        return _name;
    }
}
