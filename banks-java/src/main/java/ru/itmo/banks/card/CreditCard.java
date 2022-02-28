package ru.itmo.banks.card;

import ru.itmo.banks.BanksException;
import ru.itmo.banks.Person;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CreditCard implements GeneralCard {
    private final int _cardId;
    private final int _personId;
    private final double _limit;
    private final GregorianCalendar _dateOfLastAccuralAPercent;
    private final double _creditLimit;
    private double _commission;
    private boolean _trusted;
    private double _cash;
    private double _notWithdrawnCommission;

    public CreditCard(int cardId, Person person, PercentInfo commission, double limit, double startCash, double creditLimit, GregorianCalendar dateOfLastAccuralAPercent) {
        _cardId = cardId;
        _personId = person.get_personId();
        _commission = commission.getPercentForCredit();
        _trusted = person.get_passport() != 0;
        _cash = startCash;
        _limit = limit;
        _notWithdrawnCommission = 0;
        _creditLimit = creditLimit;
        _dateOfLastAccuralAPercent = dateOfLastAccuralAPercent;
    }

    @Override
    public void withdrawal(double cashForWithdrawal) throws BanksException {
        if (!_trusted && cashForWithdrawal > _limit)
            throw new BanksException("Exceeding limit Exception");
        if (cashForWithdrawal > _cash)
            throw new BanksException("Withdrawal Cash More Than In Card Exception");
        _cash -= cashForWithdrawal;
    }

    @Override
    public void replenishment(double cashForReplenishment) {
        _cash += cashForReplenishment;
        if (_cash - cashForReplenishment < 0)
            _cash -= _notWithdrawnCommission;
    }

    @Override
    public void transfer(GeneralCard otherCard, double cash) throws BanksException {
        withdrawal(cash);
        otherCard.replenishment(cash);
    }

    @Override
    public void changePercent(PercentInfo percentInfo) {
        _commission = percentInfo.getPercentForDebet();
    }

    @Override
    public void calculatePercent(GregorianCalendar newDate) {
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
    public int getCardId() {
        return _cardId;
    }

    @Override
    public int getPersonId() {
        return _personId;
    }

    @Override
    public void setTrusted(boolean newTrust) {
        _trusted = newTrust;
    }

    @Override
    public double getCash() {
        return _cash;
    }

    @Override
    public GregorianCalendar getDate() {
        return _dateOfLastAccuralAPercent;
    }
}
