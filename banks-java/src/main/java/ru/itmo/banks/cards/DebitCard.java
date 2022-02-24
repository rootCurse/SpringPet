package ru.itmo.banks.cards;

import ru.itmo.banks.BanksException;
import ru.itmo.banks.Person;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DebitCard implements GeneralCard{
    private int _cardId;
    private int _personId;
    private double _limit;
    private GregorianCalendar _dateOfLastAccuralAPercent;
    private double _percentOnRemainder;
    private boolean _trusted;
    private double _cash;
    private double _notAccruedCash;

    public DebitCard(int cardId, Person person, PercentInfo percentOnRemainder, double limit, double startCash, GregorianCalendar dateOfLastAccuralAPercent)
    {
        _cardId = cardId;
        _personId = person.get_personId();
        _percentOnRemainder = percentOnRemainder.get_percentForDebet();
        if (person.get_passport() == 0)
            _trusted = false;
        else
            _trusted = true;
        _cash = startCash;
        _limit = limit;
        _notAccruedCash = 0;
        _dateOfLastAccuralAPercent = dateOfLastAccuralAPercent;
    }

    @Override
    public void Withdrawal(double cashForWithdrawal) throws BanksException {
        if (!_trusted && cashForWithdrawal > _limit)
            throw new BanksException("Exceeding limit Exception");
        if (cashForWithdrawal > _cash)
            throw new BanksException("Withdrawal Cash More Than In Card Exception");
        _cash -= cashForWithdrawal;
    }

    @Override
    public void Replenishment(double cashForReplenishment) {
        _cash += cashForReplenishment;
    }

    @Override
    public void Transfer(GeneralCard otherCard, double cash) throws BanksException {
        Withdrawal(cash);
        otherCard.Replenishment(cash);
    }

    @Override
    public void CalculatePercent(GregorianCalendar newDate) {
        while (!_dateOfLastAccuralAPercent.equals(newDate)) {
            System.out.println(_dateOfLastAccuralAPercent.getTime());
            _dateOfLastAccuralAPercent.add(GregorianCalendar.DAY_OF_MONTH, 1);
            if (_dateOfLastAccuralAPercent.get(GregorianCalendar.DAY_OF_MONTH) == 1) {
                _cash += _notAccruedCash;
                _notAccruedCash = 0;
            }
            if (_cash > 0)
                _notAccruedCash += _cash * _percentOnRemainder;
        }
    }

    @Override
    public void ChangePercent(PercentInfo percentInfo) {
        _percentOnRemainder = percentInfo.get_percentForDebet();
    }

    @Override
    public int GetCardId() {
        return _cardId;
    }

    @Override
    public int GetPersonId() {
        return _personId;
    }

    @Override
    public void SetTrusted(boolean newTrust) {
        _trusted = newTrust;
    }

    @Override
    public double GetCash() {
        return _cash;
    }

    @Override
    public GregorianCalendar GetDate() {
        return _dateOfLastAccuralAPercent;
    }
}
