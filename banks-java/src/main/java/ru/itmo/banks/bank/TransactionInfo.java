package ru.itmo.banks.bank;

import ru.itmo.banks.card.GeneralCard;

public class TransactionInfo {

    private final GeneralCard _cardFrom;
    private final GeneralCard _cardTo;
    private final int _transactionId;
    private final double _cash;

    public TransactionInfo(GeneralCard card1, GeneralCard card2, int transactionId, double cash) {
        _cardFrom = card1;
        _cardTo = card2;
        _transactionId = transactionId;
        _cash = cash;
    }

    public GeneralCard getCardIn() {
        return _cardFrom;
    }

    public GeneralCard getCardTo() {
        return _cardTo;
    }

    public int getTransactionId() {
        return _transactionId;
    }

    public double getCash() {
        return _cash;
    }
}

