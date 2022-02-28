package ru.itmo.banks.bank;

import ru.itmo.banks.BanksException;
import ru.itmo.banks.Person;
import ru.itmo.banks.card.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class CentralBank {
    private static CentralBank _instance;
    private final List<TransactionInfo> _transactions;
    private final List<Bank> _banks;
    private int _bankId;
    private int _personId;
    private int _transactionId;
    private int _cardId;
    private GregorianCalendar _currentDate;

    public CentralBank() {
        _bankId = 0;
        _personId = 0;
        _transactionId = 0;
        _cardId = 0;
        _currentDate = new GregorianCalendar();
        _currentDate.set(Calendar.HOUR, 12);
        _currentDate.set(Calendar.MINUTE, 0);
        _currentDate.set(Calendar.SECOND, 0);
        _currentDate.set(Calendar.MILLISECOND, 0);
        _transactions = new ArrayList<TransactionInfo>();
        _banks = new ArrayList<Bank>();
    }

    public static CentralBank getInstance() {
        if (_instance == null) {
            _instance = new CentralBank();
        }

        return _instance;
    }

    public Bank addBank(double creditLimit, double limitWithoutTrusted, PercentInfo percentInfo) {
        Bank bank = new Bank(_bankId, creditLimit, limitWithoutTrusted, _currentDate, percentInfo);
        _banks.add(bank);
        _bankId++;
        return bank;
    }

    public Person addPerson(String firstName, String secondName, Bank bank) {
        Person person = new Person(firstName, secondName, _personId);
        bank.addPerson(person);
        _personId++;
        return person;
    }

    public void addPersonDocument(int personId, int document) {
        Person person = findPerson(personId);
        person.set_passport(document);
    }

    public void addAddress(int personId, String address) {
        Person person = findPerson(personId);
        person.set_address(address);
    }

    public GeneralCard addDebitCard(int personId, double startCash, Bank bank) throws BanksException {
        Person person = findPerson(personId);
        if (person == null)
            throw new BanksException("Person Not Found");
        GeneralCard card = new DebitCard(_cardId, person, bank.getPercents(), bank.getLimitWithoutTrusted(), startCash, _currentDate);
        bank.addCard(card, person);
        _cardId++;
        return card;
    }

    public GeneralCard addCreditCard(int personId, double startCash, Bank bank) throws BanksException {
        Person person = findPerson(personId);
        if (person == null)
            throw new BanksException("Person Not Found");
        GeneralCard card = new CreditCard(_cardId, person, bank.getPercents(), bank.getLimitWithoutTrusted(), startCash, bank.getCreditLimit(), _currentDate);
        bank.addCard(card, person);
        _cardId++;
        return card;
    }

    public GeneralCard addDepositCard(int personId, double startCash, Bank bank) throws BanksException {
        Person person = findPerson(personId);
        if (person == null)
            throw new BanksException("Person Not Found");
        GeneralCard card = new DepositCard(_cardId, person, bank.getPercents(), bank.getLimitWithoutTrusted(), startCash, _currentDate, bank.getAccountTerm());
        bank.addCard(card, person);
        _cardId++;
        return card;
    }

    public void withdrawal(int cardId, double cashForWithdrawal) throws BanksException {
        GeneralCard card = findCard(cardId);
        if (card == null)
            throw new BanksException("card not found");
        card.withdrawal(cashForWithdrawal);
    }

    public void replenishment(int cardId, double cash) throws BanksException {
        GeneralCard card = findCard(cardId);
        if (card == null)
            throw new BanksException("card not found");
        card.replenishment(cash);
    }

    public void transfer(int cardIdFrom, int cardIdTo, double cash) throws BanksException {
        GeneralCard cardFrom = findCard(cardIdFrom);
        if (cardFrom == null)
            throw new BanksException("card not found");
        GeneralCard cardTo = findCard(cardIdTo);
        if (cardTo == null)
            throw new BanksException("card not found");
        cardFrom.transfer(cardTo, cash);
        _transactions.add(new TransactionInfo(cardFrom, cardTo, _transactionId, cash));
        _transactionId++;
    }

    public void attachObserver(ru.itmo.banks.observer.Observer observer, Bank bank) {
        bank.attach(observer);
    }

    public void cancelTransaction(int transactionId) throws BanksException {
        TransactionInfo transaction = findTransaction(transactionId);
        if (transaction == null)
            throw new BanksException("Cannot Find Transaction");
        transaction.getCardTo().transfer(transaction.getCardIn(), transaction.getCash());
        _transactions.remove(transaction);
    }

    public void changeDate(GregorianCalendar date) {
        _currentDate = date;
        System.out.println(date.getTime());
        for (Bank bank : _banks) {
            bank.changeData(_currentDate);
        }
    }

    public void changePercent(PercentInfo percentInfo, Bank bank) {
        bank.changeInterest(percentInfo);
    }

    public List<Bank> getBanks() {
        return _banks;
    }

    public Bank findBank(int bankId) {
        for (Bank bank : _banks)
            if (bank.getBankId() == bankId)
                return bank;
        return null;
    }

    private TransactionInfo findTransaction(int transactionId) {
        TransactionInfo transaction = null;
        for (TransactionInfo transactionInfo : _transactions) {
            if (transactionInfo.getTransactionId() == transactionId) {
                transaction = transactionInfo;
                break;
            }
        }
        return transaction;
    }

    private Person findPerson(int personId) {
        Person person = null;
        for (Bank bank : _banks) {
            for (Person personInBank : bank.getPersons())
                if (personInBank.get_personId() == personId) {
                    person = personInBank;
                    break;
                }
            if (person != null)
                break;
        }
        return person;
    }

    private GeneralCard findCard(int cardId) {
        GeneralCard result = null;
        for (Bank bank : _banks) {
            for (GeneralCard card : bank.get_Cards())
                if (card.getCardId() == cardId) {
                    result = card;
                    break;
                }
            if (result != null)
                break;
        }
        return result;
    }
}

