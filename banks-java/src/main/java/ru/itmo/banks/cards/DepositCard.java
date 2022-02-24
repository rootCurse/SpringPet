package ru.itmo.banks.cards;

import ru.itmo.banks.BanksException;
import ru.itmo.banks.Person;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DepositCard implements GeneralCard{
    private  int _cardId;
    private  int _personId;
    private  double _limit;
    private  GregorianCalendar _dateOfLastAccuralAPercent;
    private GregorianCalendar _dateOfStartUsedCard;
    private double _percentOnRemainder;
    private boolean _trusted;
    private double _startCash;
    private double _cash;
    private double _notAccruedCash;

    public DepositCard(int cardId, Person person, PercentInfo percentInfo, double limit, double startCash, GregorianCalendar dateOfCreate, GregorianCalendar accountTerm) {
        _cardId = cardId;
        _personId = person.get_personId();
        _percentOnRemainder = percentInfo.GetPercentForDeposit(startCash);
        if ( person.get_passport() == 0)
            _trusted = false;
        else
            _trusted = true;
        _cash = startCash;
        _startCash = startCash;
        _limit = limit;
        _dateOfLastAccuralAPercent = dateOfCreate;
        _dateOfStartUsedCard = accountTerm;
        _notAccruedCash = 0;
    }

    @Override
    public void Withdrawal(double cashForWithdrawal) throws BanksException {
        if (!_trusted && cashForWithdrawal > _limit)
            throw new BanksException("Exceeding limit Exception");
        if (cashForWithdrawal > _cash)
            throw new BanksException("Withdrawal Cash More Than In Card Exception");
        if (_dateOfLastAccuralAPercent.before(_dateOfStartUsedCard))
            throw new BanksException("Account Has Not Yet Expired Exception");
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
