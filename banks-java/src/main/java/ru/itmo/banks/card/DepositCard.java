package ru.itmo.banks.card;

import ru.itmo.banks.BanksException;
import ru.itmo.banks.Person;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DepositCard implements GeneralCard {
    private final int _cardId;
    private final int _personId;
    private final double _limit;
    private final GregorianCalendar _dateOfLastAccuralAPercent;
    private final GregorianCalendar _dateOfStartUsedCard;
    private double _percentOnRemainder;
    private boolean _trusted;
    private final double _startCash;
    private double _cash;
    private double _notAccruedCash;

    public DepositCard(int cardId, Person person, PercentInfo percentInfo, double limit, double startCash, GregorianCalendar dateOfCreate, GregorianCalendar accountTerm) {
        _cardId = cardId;
        _personId = person.get_personId();
        _percentOnRemainder = percentInfo.getPercentForDeposit(startCash);
        _trusted = person.get_passport() != 0;
        _cash = startCash;
        _startCash = startCash;
        _limit = limit;
        _dateOfLastAccuralAPercent = dateOfCreate;
        _dateOfStartUsedCard = accountTerm;
        _notAccruedCash = 0;
    }

    @Override
    public void withdrawal(double cashForWithdrawal) throws BanksException {
        if (!_trusted && cashForWithdrawal > _limit)
            throw new BanksException("Exceeding limit Exception");
        if (cashForWithdrawal > _cash)
            throw new BanksException("Withdrawal Cash More Than In Card Exception");
        if (_dateOfLastAccuralAPercent.before(_dateOfStartUsedCard))
            throw new BanksException("Account Has Not Yet Expired Exception");
        _cash -= cashForWithdrawal;
    }

    @Override
    public void replenishment(double cashForReplenishment) {
        _cash += cashForReplenishment;
    }

    @Override
    public void transfer(GeneralCard otherCard, double cash) throws BanksException {
        withdrawal(cash);
        otherCard.replenishment(cash);
    }

    @Override
    public void calculatePercent(GregorianCalendar newDate) {
        while (!_dateOfLastAccuralAPercent.equals(newDate)) {
            if (_dateOfLastAccuralAPercent.get(Calendar.DAY_OF_MONTH) == 1) {
                _cash += _notAccruedCash;
                _notAccruedCash = 0;
            }
            if (_cash > 0)
                _notAccruedCash += _cash * _percentOnRemainder;
            _dateOfLastAccuralAPercent.add(Calendar.DAY_OF_YEAR, 1);
        }
    }

    @Override
    public void changePercent(PercentInfo percentInfo) {
        _percentOnRemainder = percentInfo.getPercentForDebet();
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
