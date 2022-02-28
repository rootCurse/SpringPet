package ru.itmo.banks;

import ru.itmo.banks.bank.Bank;
import ru.itmo.banks.bank.CentralBank;
import ru.itmo.banks.card.GeneralCard;

import java.util.GregorianCalendar;
import java.util.Scanner;

public class UserConsole {
    private final Person _person;
    private final CentralBank _bank;
    private final String _listOfAction;

    public UserConsole(Person person) {
        _person = person;
        _bank = CentralBank.getInstance();
        _listOfAction = "Choose action:\n ListOfCard\n ListOfBank\n AddDebitCard\n AddCreditCard\n AddDepositCard\n Withdrawal\n Replenishment\n Transfer\n ChangeDate\n Exit";
    }

    public void start() throws BanksException {
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
                    listOfCard();
                    break;
                case "ListOfBank":
                    listOfBank();
                    break;
                case "AddDebitCard":
                    System.out.println("Enter cash");
                    cash = scanner.nextDouble();
                    listOfBank();
                    System.out.println("Choose bank");
                    id = scanner.nextInt();
                    addDebitCard(cash, id);
                    System.out.println("Success");
                    break;
                case "AddCreditCard":
                    System.out.println("Enter cash");
                    cash = scanner.nextDouble();
                    listOfBank();
                    System.out.println("Choose bank");
                    id = scanner.nextInt();
                    addCreditCard(cash, id);
                    System.out.println("Success");
                    break;
                case "AddDepositCard":
                    System.out.println("Enter cash");
                    cash = scanner.nextDouble();
                    listOfBank();
                    System.out.println("Choose bank");
                    id = scanner.nextInt();
                    addDepositCard(cash, id);
                    System.out.println("Success");
                    break;
                case "Withdrawal":
                    System.out.println("Enter cash");
                    cash = scanner.nextDouble();
                    listOfCard();
                    System.out.println("Choose card");
                    id = scanner.nextInt();
                    withdrawal(id, cash);
                    System.out.println("Success");
                    break;
                case "Replenishment":
                    System.out.println("Enter cash");
                    cash = scanner.nextDouble();
                    listOfCard();
                    System.out.println("Choose card");
                    id = scanner.nextInt();
                    replenishment(id, cash);
                    System.out.println("Success");
                    break;
                case "Transfer":
                    listOfCard();
                    System.out.println("Enter cash");
                    cash = scanner.nextDouble();
                    System.out.println("Choose card1");
                    id = scanner.nextInt();
                    System.out.println("Choose card2");
                    transfer(id, scanner.nextInt(), cash);
                    System.out.println("Success");
                    break;
                case "Exit":
                    System.out.println("Exit of console");
                    break;
                default:
                    System.out.println("Unknown command");
                    break;

            }
        } while (!choose.equals("Exit"));
    }

    public void listOfCard() {
        System.out.println("List of Card: id - cash");
        for (GeneralCard card : _person.get_cards()) {
            System.out.println(card.getCardId() + "\t" + card.getCash());
        }
    }

    public void listOfBank() {
        System.out.println("BankID: ");
        for (Bank bank : _bank.getBanks())
            System.out.println(bank.getBankId());
    }

    private void addDebitCard(double startCash, int bankId) throws BanksException {
        _bank.addDebitCard(_person.get_personId(), startCash, _bank.findBank(bankId));
    }

    private void addCreditCard(double startCash, int bankId) throws BanksException {
        _bank.addCreditCard(_person.get_personId(), startCash, _bank.findBank(bankId));
    }

    private void addDepositCard(double startCash, int bankId) throws BanksException {
        _bank.addDepositCard(_person.get_personId(), startCash, _bank.findBank(bankId));
    }

    private void withdrawal(int cardId, double cash) throws BanksException {
        _bank.withdrawal(cardId, cash);
    }

    private void replenishment(int cardId, double cash) throws BanksException {
        _bank.replenishment(cardId, cash);
    }

    private void transfer(int cardId1, int cardId2, double cash) throws BanksException {
        _bank.transfer(cardId1, cardId2, cash);
    }

    private void changeDate(GregorianCalendar newDate) {
        _bank.changeDate(newDate);
    }
}
