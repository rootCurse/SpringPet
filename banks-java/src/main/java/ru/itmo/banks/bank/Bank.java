package ru.itmo.banks.bank;

import ru.itmo.banks.Person;
import ru.itmo.banks.card.GeneralCard;
import ru.itmo.banks.card.PercentInfo;
import ru.itmo.banks.observer.Observer;
import ru.itmo.banks.observer.Subject;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Bank implements Subject {

    private final List<GeneralCard> _cards;
    private final List<Person> _persons;
    private final List<Observer> _observers;
    private final int _bankId;
    private PercentInfo _percents;
    private double _limitWithoutTrusted;
    private double _creditLimit;
    private GregorianCalendar _accountTerm;


    public Bank(int bankId, double creditLimit, double limitWithoutTrusted, GregorianCalendar accountTerm, PercentInfo percentInfo) {
        _bankId = bankId;
        _limitWithoutTrusted = limitWithoutTrusted;
        _creditLimit = creditLimit;
        _accountTerm = accountTerm;
        _percents = percentInfo;
        _cards = new ArrayList<GeneralCard>();
        _persons = new ArrayList<Person>();
        _observers = new ArrayList<Observer>();
    }

    public void addPerson(Person person) {
        _persons.add(person);
    }

    public void addCard(GeneralCard card, Person person) {
        _cards.add(card);
        person.addCard(card);
    }

    public Person get_Person(int personId) {
        Person findPerson = null;
        for (Person person : _persons) {
            if (person.get_personId() == personId) {
                findPerson = person;
                break;
            }
        }
        return findPerson;
    }

    public List<GeneralCard> get_Cards() {
        return _cards;
    }

    public void changeInterest(PercentInfo percentInfo) {
        _percents = percentInfo;
        for (GeneralCard card : _cards) {
            card.changePercent(percentInfo);
        }

        notifyForObserver();
    }

    public void changeData(GregorianCalendar date) {
        this._accountTerm = date;
        for (GeneralCard card : _cards) {
            card.calculatePercent(date);
        }

        for (Person person : _persons) {
            if (person.get_passport() != 0)
                for (GeneralCard card : _cards)
                    card.setTrusted(true);
        }
    }

    public void attach(Observer observer) {
        _observers.add(observer);
    }

    public void detach(Observer observer) {
        _observers.remove(observer);
    }

    public void notifyForObserver() {
        for (Observer observer : _observers) {
            observer.update(this);
        }
    }

    public List<Person> getPersons() {
        return _persons;
    }

    public List<Observer> get_Observers() {
        return _observers;
    }

    public double getLimitWithoutTrusted() {
        return _limitWithoutTrusted;
    }

    public void set_limitWithoutTrusted(double _limitWithoutTrusted) {
        this._limitWithoutTrusted = _limitWithoutTrusted;
    }

    public double getCreditLimit() {
        return _creditLimit;
    }

    public void setCreditLimit(double _creditLimit) {
        this._creditLimit = _creditLimit;
    }

    public PercentInfo getPercents() {
        return _percents;
    }

    public void setPercents(PercentInfo _percents) {
        this._percents = _percents;
    }

    public GregorianCalendar getAccountTerm() {
        return _accountTerm;
    }

    public int getBankId() {
        return _bankId;
    }
}

