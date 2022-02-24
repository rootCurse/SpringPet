package ru.itmo.banks;

import ru.itmo.banks.cards.GeneralCard;
import ru.itmo.banks.observer.Observer;
import ru.itmo.banks.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class Person implements Observer {

    private String _firstName;
    private String _secondName;
    private int _personId;
    private String _address;
    private int _passport;
    private List<GeneralCard> _cards;

    public Person(String firstName, String secondName, int id) {
        _firstName = firstName;
        _secondName = secondName;
        _personId = id;
        _passport = 0;
        _address = null;
        _cards = new ArrayList<GeneralCard>();
    }

    public void AddCard(GeneralCard card)
    {
        _cards.add(card);
    }

    @Override
    public void Update(Subject iSubject) {
        System.out.println("Change in bank conditions");
    }

    public String get_firstName() {
        return _firstName;
    }

    public String get_secondName() {
        return _secondName;
    }

    public int get_personId() {
        return _personId;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }

    public int get_passport() {
        return _passport;
    }

    public void set_passport(int _passport) {
        this._passport = _passport;
    }
    public List<GeneralCard> get_cards()
    {
        return _cards;
    }
}
