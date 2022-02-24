package ru.itmo.banks.banks;

import ru.itmo.banks.cards.GeneralCard;

public class TransactionInfo {

    private GeneralCard _card1;
    private GeneralCard _card2;
    private int _transactionId;
    private double _cash;

    public TransactionInfo(GeneralCard card1, GeneralCard card2, int transactionId, double cash)
    {
        _card1 = card1;
        _card2 = card2;
        _transactionId = transactionId;
        _cash = cash;
    }

    public GeneralCard get_card1() {
        return _card1;
    }

    public GeneralCard get_card2() {
        return _card2;
    }

    public int get_transactionId() {
        return _transactionId;
    }

    public double get_cash() {
        return _cash;
    }
}

