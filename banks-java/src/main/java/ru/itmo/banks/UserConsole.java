package ru.itmo.banks;

import ru.itmo.banks.banks.Bank;
import ru.itmo.banks.banks.CentralBank;
import ru.itmo.banks.cards.GeneralCard;

import java.util.GregorianCalendar;
import java.util.Scanner;

public class UserConsole {
    private Person _person;
    private CentralBank _bank;
    private String _listOfAction;

    public UserConsole(Person person){
        _person = person;
        _bank = CentralBank.GetInstance();
        _listOfAction = "Choose action:\n ListOfCard\n ListOfBank\n AddDebitCard\n AddCreditCard\n AddDepositCard\n Withdrawal\n Replenishment\n Transfer\n ChangeDate\n Exit";
    }

    public void Start() throws BanksException {
        System.out.println("Hello " + _person.get_firstName() + " " + _person.get_secondName());
        String choose = null;
        Scanner scanner = new Scanner(System.in);
        int id = 0;
        double cash = 0;
        do {
            System.out.println(_listOfAction);
            choose = scanner.next();
            switch (choose) {
                case "ListOfCard":
                    ListOfCard();
                    break;
                case "ListOfBank":
                    ListOfBank();
                    break;
                case "AddDebitCard":
                    System.out.println("Enter cash");
                    cash = scanner.nextDouble();
                    ListOfBank();
                    System.out.println("Choose bank");
                    id = scanner.nextInt();
                    AddDebitCard(cash, id);
                    System.out.println("Success");
                    break;
                case "AddCreditCard":
                    System.out.println("Enter cash");
                    cash = scanner.nextDouble();
                    ListOfBank();
                    System.out.println("Choose bank");
                    id = scanner.nextInt();
                    AddCreditCard(cash, id);
                    System.out.println("Success");
                    break;
                case "AddDepositCard":
                    System.out.println("Enter cash");
                    cash = scanner.nextDouble();
                    ListOfBank();
                    System.out.println("Choose bank");
                    id = scanner.nextInt();
                    AddDepositCard(cash, id);
                    System.out.println("Success");
                    break;
                case "Withdrawal":
                    System.out.println("Enter cash");
                    cash = scanner.nextDouble();
                    ListOfCard();
                    System.out.println("Choose card");
                    id = scanner.nextInt();
                    Withdrawal(id, cash);
                    System.out.println("Success");
                    break;
                case "Replenishment":
                    System.out.println("Enter cash");
                    cash = scanner.nextDouble();
                    ListOfCard();
                    System.out.println("Choose card");
                    id = scanner.nextInt();
                    Replenishment(id, cash);
                    System.out.println("Success");
                    break;
                case "Transfer":
                    ListOfCard();
                    System.out.println("Enter cash");
                    cash = scanner.nextDouble();
                    System.out.println("Choose card1");
                    id = scanner.nextInt();
                    System.out.println("Choose card2");
                    Transfer(id, scanner.nextInt(),cash);
                    System.out.println("Success");
                    break;
                case "Exit":
                    System.out.println("Exit of console");
                    break;
                default:
                    System.out.println("Unknown command");
                    break;

            }
        }while(!choose.equals("Exit"));
    }

    public void ListOfCard(){
        System.out.println("List of Card: id - cash");
        for (GeneralCard card : _person.get_cards()){
            System.out.println(card.GetCardId() + "\t" + card.GetCash());
        }
    }

    public void ListOfBank(){
        System.out.println("BankID: ");
        for(Bank bank : _bank.GetBanks())
            System.out.println(bank.get_bankId());
    }

    private void AddDebitCard(double startCash, int bankId) throws BanksException {
        _bank.AddDebitCard(_person.get_personId(), startCash, _bank.FindBank(bankId));
    }

    private void AddCreditCard(double startCash, int bankId) throws BanksException {
        _bank.AddCreditCard(_person.get_personId(), startCash, _bank.FindBank(bankId));
    }

    private void AddDepositCard(double startCash, int bankId) throws BanksException {
        _bank.AddDepositCard(_person.get_personId(), startCash, _bank.FindBank(bankId));
    }

    private void Withdrawal(int cardId, double cash) throws BanksException {
        _bank.Withdrawal(cardId, cash);
    }

    private void Replenishment(int cardId, double cash) throws BanksException {
        _bank.Replenishment(cardId, cash);
    }

    private void Transfer(int cardId1, int cardId2, double cash) throws BanksException {
        _bank.Transfer(cardId1, cardId2, cash);
    }

    private void ChangeDate(GregorianCalendar newDate){
        _bank.ChangeDate(newDate);
    }
}
