package ru.itmo.banks.card;

import ru.itmo.banks.BanksException;
import ru.itmo.banks.Person;

import java.util.GregorianCalendar;

public class DebitCard implements GeneralCard {
    private final int _cardId;
    private final int _personId;
    private final double _limit;
    private final GregorianCalendar _dateOfLastAccuralAPercent;
    private double _percentOnRemainder;
    private boolean _trusted;
    private double _cash;
    private double _notAccruedCash;

    public DebitCard(int cardId, Person person, PercentInfo percentOnRemainder, double limit, double startCash, GregorianCalendar dateOfLastAccuralAPercent) {
        _cardId = cardId;
        _personId = person.get_personId();
        _percentOnRemainder = percentOnRemainder.getPercentForDebet();
        _trusted = person.get_passport() != 0;
        _cash = startCash;
        _limit = limit;
        _notAccruedCash = 0;
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
    }

    @Override
    public void transfer(GeneralCard otherCard, double cash) throws BanksException {
        withdrawal(cash);
        otherCard.replenishment(cash);
    }

    @Override
    public void calculatePercent(GregorianCalendar newDate) {
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
