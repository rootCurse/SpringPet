package ru.itmo.banks.cards;

import ru.itmo.banks.BanksException;
import ru.itmo.banks.Person;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CreditCard implements GeneralCard{
    private int _cardId;
    private int _personId;
    private double _limit;
    private GregorianCalendar _dateOfLastAccuralAPercent;
    private double _creditLimit;
    private double _commission;
    private boolean _trusted;
    private double _cash;
    private double _notWithdrawnCommission;

    public CreditCard(int cardId, Person person, PercentInfo commission, double limit, double startCash, double creditLimit, GregorianCalendar dateOfLastAccuralAPercent)
    {
        _cardId = cardId;
        _personId = person.get_personId();
        _commission = commission.get_percentForCredit();
        if (person.get_passport() == 0)
            _trusted = false;
        else
            _trusted = true;
        _cash = startCash;
        _limit = limit;
        _notWithdrawnCommission = 0;
        _creditLimit = creditLimit;
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
        if (_cash - cashForReplenishment < 0)
            _cash -= _notWithdrawnCommission;
    }

    @Override
    public void Transfer(GeneralCard otherCard, double cash) throws BanksException {
        Withdrawal(cash);
        otherCard.Replenishment(cash);
    }

    @Override
    public void ChangePercent(PercentInfo percentInfo) {
        _commission = percentInfo.get_percentForDebet();
    }

    @Override
    public void CalculatePercent(GregorianCalendar newDate) {
        while (!_dateOfLastAccuralAPercent.equals(newDate)) {
            if (_dateOfLastAccuralAPercent.get(Calendar.DAY_OF_MONTH) == 1) {
                _cash += _notWithdrawnCommission;
                _notWithdrawnCommission = 0;
            }
            if (_cash < 0)
                _notWithdrawnCommission += _cash * _commission;
            _dateOfLastAccuralAPercent.add(Calendar.DAY_OF_YEAR, 1);
        }
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
