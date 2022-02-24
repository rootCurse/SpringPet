package ru.itmo.banks.cards;

import ru.itmo.banks.BanksException;

import java.util.GregorianCalendar;

public interface GeneralCard {
    void Withdrawal(double cashForWithdrawal) throws BanksException;
    void Replenishment(double cashForReplenishment);
    void Transfer(GeneralCard otherCard, double cash) throws BanksException;
    void ChangePercent(PercentInfo percentInfo);
    void CalculatePercent(GregorianCalendar newDate);
    int GetCardId();
    int GetPersonId();
    void SetTrusted(boolean newTrust);
    double GetCash();
    GregorianCalendar GetDate();
}
