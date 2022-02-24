package ru.itmo.banks.banks;

import ru.itmo.banks.Person;
import ru.itmo.banks.cards.GeneralCard;
import ru.itmo.banks.observer.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import ru.itmo.banks.cards.PercentInfo;

public class Bank implements Subject {

    private List<GeneralCard> _cards;
    private List<Person> _persons;
    private List<Observer> _observers;
    private int _bankId;
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

    public void AddPerson(Person person)
    {
        _persons.add(person);
    }

    public void AddCard(GeneralCard card, Person person) {
        _cards.add(card);
        person.AddCard(card);
    }

    public Person GetPerson(int personId) {
        Person findPerson = null;
        for (Person person:_persons) {
            if (person.get_personId() == personId){
                findPerson = person;
                break;
            }
        }
        return findPerson;
    }

    public List<GeneralCard> GetCards()
    {
        return _cards;
    }

    public void ChangeInterest(PercentInfo percentInfo) {
        _percents = percentInfo;
        for(GeneralCard card : _cards) {
            card.ChangePercent(percentInfo);
        }

        Notify();
    }

    public void ChangeData(GregorianCalendar date) {
        this._accountTerm = date;
        for (GeneralCard card : _cards) {
            card.CalculatePercent(date);
        }

        for(Person person : _persons){
            if(person.get_passport() != 0)
                for(GeneralCard card : _cards)
                    card.SetTrusted(true);
        }
    }

    public void Attach(Observer observer) {
        _observers.add(observer);
    }

    public void Detach(Observer observer) {
        _observers.remove(observer);
    }

    public void Notify() {
        for (Observer observer : _observers)
        {
            observer.Update(this);
        }
    }

    public List<Person> GetPersons() {
        return _persons;
    }

    public List<Observer> GetObservers() {
        return _observers;
    }

    public double get_limitWithoutTrusted() {
        return _limitWithoutTrusted;
    }

    public void set_limitWithoutTrusted(double _limitWithoutTrusted) {
        this._limitWithoutTrusted = _limitWithoutTrusted;
    }

    public double get_creditLimit() {
        return _creditLimit;
    }

    public void set_creditLimit(double _creditLimit) {
        this._creditLimit = _creditLimit;
    }

    public PercentInfo get_percents() {
        return _percents;
    }

    public void set_percents(PercentInfo _percents) {
        this._percents = _percents;
    }

    public GregorianCalendar get_accountTerm() {
        return _accountTerm;
    }

    public int get_bankId() {
        return _bankId;
    }
}

