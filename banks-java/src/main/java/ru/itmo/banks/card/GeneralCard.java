package ru.itmo.banks.card;

import ru.itmo.banks.BanksException;

import java.util.GregorianCalendar;

public interface GeneralCard {
    void withdrawal(double cashForWithdrawal) throws BanksException;

    void replenishment(double cashForReplenishment);

    void transfer(GeneralCard otherCard, double cash) throws BanksException;

    void changePercent(PercentInfo percentInfo);

    void calculatePercent(GregorianCalendar newDate);

    int getCardId();

    int getPersonId();

    void setTrusted(boolean newTrust);

    double getCash();

    GregorianCalendar getDate();
}
