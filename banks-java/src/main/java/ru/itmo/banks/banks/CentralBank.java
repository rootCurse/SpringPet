package ru.itmo.banks.banks;

import ru.itmo.banks.BanksException;
import ru.itmo.banks.Person;
import ru.itmo.banks.cards.*;

import java.util.*;

public class CentralBank {
    private static CentralBank _instance;
    private List<TransactionInfo> _transactions;
    private List<Bank> _banks;
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

    public static CentralBank GetInstance() {
        if (_instance == null)
        {
            _instance = new CentralBank();
        }

        return _instance;
    }

    public Bank AddBank(double creditLimit, double limitWithoutTrusted, PercentInfo percentInfo) {
        Bank bank = new Bank(_bankId, creditLimit, limitWithoutTrusted, _currentDate, percentInfo);
        _banks.add(bank);
        _bankId++;
        return bank;
    }

    public Person AddPerson(String firstName, String secondName, Bank bank) {
        Person person = new Person(firstName, secondName, _personId);
        bank.AddPerson(person);
        _personId++;
        return person;
    }

    public void AddPersonDocument(int personId, int document) {
        Person person = FindPerson(personId);
        person.set_passport(document);
    }

    public void AddAddress(int personId, String address) {
        Person person = FindPerson(personId);
        person.set_address(address);
    }

    public GeneralCard AddDebitCard(int personId, double startCash, Bank bank) throws BanksException {
        Person person = FindPerson(personId);
        if (person == null)
            throw new BanksException("Person Not Found");
        GeneralCard card = new DebitCard(_cardId, person, bank.get_percents(), bank.get_limitWithoutTrusted(), startCash, _currentDate);
        bank.AddCard(card, person);
        _cardId++;
        return card;
    }

    public GeneralCard AddCreditCard(int personId, double startCash, Bank bank) throws BanksException {
        Person person = FindPerson(personId);
        if (person == null)
            throw new BanksException("Person Not Found");
        GeneralCard card = new CreditCard(_cardId, person, bank.get_percents(), bank.get_limitWithoutTrusted(), startCash, bank.get_creditLimit(), _currentDate);
        bank.AddCard(card, person);
        _cardId++;
        return card;
    }

    public GeneralCard AddDepositCard(int personId, double startCash, Bank bank) throws BanksException {
        Person person = FindPerson(personId);
        if (person == null)
            throw new BanksException("Person Not Found");
        GeneralCard card = new DepositCard(_cardId, person, bank.get_percents(), bank.get_limitWithoutTrusted(), startCash, _currentDate, bank.get_accountTerm());
        bank.AddCard(card, person);
        _cardId++;
        return card;
    }

    public void Withdrawal(int cardId, double cashForWithdrawal) throws BanksException {
        GeneralCard card = FindCard(cardId);
        if (card == null)
            throw new BanksException("card not found");
        card.Withdrawal(cashForWithdrawal);
    }

    public void Replenishment(int cardId, double cash) throws BanksException {
        GeneralCard card = FindCard(cardId);
        if (card == null)
            throw new BanksException("card not found");
        card.Replenishment(cash);
    }

    public void Transfer(int cardId1, int cardId2, double cash) throws BanksException {
        GeneralCard card1 = FindCard(cardId1);
        if (card1 == null)
            throw new BanksException("card not found");
        GeneralCard card2 = FindCard(cardId2);
        if (card2 == null)
            throw new BanksException("card not found");
        card1.Transfer(card2, cash);
        _transactions.add(new TransactionInfo(card1, card2, _transactionId, cash));
        _transactionId++;
    }

    public void AttachObserver(ru.itmo.banks.observer.Observer observer, Bank bank) {
        bank.Attach(observer);
    }

    public void CancelTransaction(int transactionId) throws BanksException {
        TransactionInfo transaction = FindTransaction(transactionId);
        if (transaction == null)
            throw new BanksException("Cannot Find Transaction");
        transaction.get_card2().Transfer(transaction.get_card1(), transaction.get_cash());
        _transactions.remove(transaction);
    }

    public void ChangeDate(GregorianCalendar date) {
        _currentDate = date;
        System.out.println(date.getTime());
        for (Bank bank : _banks) {
            bank.ChangeData(_currentDate);
        }
    }

    public void ChangePercent(PercentInfo percentInfo, Bank bank) {
        bank.ChangeInterest(percentInfo);
    }

    public List<Bank> GetBanks() {
        return _banks;
    }

    public Bank FindBank(int bankId){
        for(Bank bank : _banks)
            if(bank.get_bankId() == bankId)
                return bank;
        return null;
    }

    private TransactionInfo FindTransaction(int transactionId) {
        TransactionInfo transaction = null;
        for(TransactionInfo transactionInfo : _transactions) {
            if(transactionInfo.get_transactionId() == transactionId) {
                transaction = transactionInfo;
                break;
            }
        }
        return transaction;
    }

    private Person FindPerson(int personId) {
        Person person = null;
        for(Bank bank: _banks) {
            for (Person personInBank : bank.GetPersons())
                if (personInBank.get_personId() == personId) {
                    person = personInBank;
                    break;
                }
            if(person != null)
                break;
        }
        return person;
    }

    private GeneralCard FindCard(int cardId) {
        GeneralCard result = null;
        for(Bank bank : _banks){
            for(GeneralCard card : bank.GetCards())
                if(card.GetCardId() == cardId) {
                    result = card;
                    break;
                }
            if(result != null)
                break;
        }
        return result;
    }
}

