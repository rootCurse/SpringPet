package ru.itmo.banks;

import javafx.util.Pair;
import ru.itmo.banks.banks.Bank;
import ru.itmo.banks.banks.CentralBank;
import ru.itmo.banks.cards.PercentInfo;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws BanksException {
        List<Pair<Double, Double>> percents = new ArrayList<Pair<Double, Double>>() {};
        percents.add(new Pair<Double, Double>(100.0, 1.0));
        CentralBank centralBank = CentralBank.GetInstance();
        Bank bank = centralBank.AddBank(100000, 10000, new PercentInfo(1, 1, percents));
        Person person = centralBank.AddPerson("Ivan", "Ivanov", bank);
        UserConsole console = new UserConsole(person);
        console.Start();
    }
}
