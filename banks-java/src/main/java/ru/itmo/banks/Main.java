package ru.itmo.banks;

import ru.itmo.banks.bank.Bank;
import ru.itmo.banks.bank.CentralBank;
import ru.itmo.banks.card.PercentInfo;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws BanksException {
        Map<Double, Double> percents = new HashMap<Double, Double>() {
        };
        percents.put(100.0, 0.01);
        CentralBank centralBank = CentralBank.getInstance();
        Bank bank = centralBank.addBank(10000, 10000, new PercentInfo(0.1, 0.15, percents));
        Person person = centralBank.addPerson("Ivan", "Ivanov", bank);
        UserConsole userConsole = new UserConsole(person);
    }
}
