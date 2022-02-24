package ru.itmo.banks;

import javafx.util.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.itmo.banks.banks.Bank;
import ru.itmo.banks.banks.CentralBank;
import ru.itmo.banks.cards.GeneralCard;
import ru.itmo.banks.cards.PercentInfo;
import sun.security.jca.GetInstance;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BanksTest {

    @Test
    void addBank(){
        List<Pair<Double, Double>> percents = new ArrayList<Pair<Double, Double>>() {};
        percents.add(new Pair<Double, Double>(100.0, 0.01));
        CentralBank centralBank = CentralBank.GetInstance();
        Bank bank = centralBank.AddBank(10000, 10000, new PercentInfo(0.1, 0.15, percents));
        Assertions.assertEquals(bank.get_creditLimit(), 10000);
        Assertions.assertEquals(bank.get_limitWithoutTrusted(), 10000);
    }

    @Test
    void addPerson() {
        List<Pair<Double, Double>> percents = new ArrayList<Pair<Double, Double>>() {};
        percents.add(new Pair<Double, Double>(100.0, 0.01));
        CentralBank centralBank = CentralBank.GetInstance();
        Bank bank = centralBank.AddBank(10000, 10000, new PercentInfo(0.1, 0.15, percents));
        Person person = centralBank.AddPerson("Ivan", "Ivanov", bank);
        Assertions.assertEquals(person.get_firstName(), "Ivan");
        Assertions.assertEquals(person.get_secondName(), "Ivanov");
        person.set_address("Nevskiy");
        person.set_passport(123);
        Assertions.assertEquals(person.get_passport(), 123);
        Assertions.assertEquals(person.get_address(), "Nevskiy");
    }

    @Test
    void addDebitCard() throws BanksException {
        List<Pair<Double, Double>> percents = new ArrayList<Pair<Double, Double>>() {};
        percents.add(new Pair<Double, Double>(100.0, 0.01));
        CentralBank centralBank = CentralBank.GetInstance();
        Bank bank = centralBank.AddBank(10000, 10000, new PercentInfo(0.1, 0.15, percents));
        Person person = centralBank.AddPerson("Ivan", "Ivanov", bank);
        person.set_address("Nevskiy");
        person.set_passport(123);
        GeneralCard debitCard = centralBank.AddDebitCard(person.get_personId(), 10000, bank);
        Assertions.assertEquals(debitCard.GetPersonId(), person.get_personId());
        Assertions.assertEquals(debitCard.GetCash(), 10000);
    }

    @Test
    void replenishment() throws BanksException {
        List<Pair<Double, Double>> percents = new ArrayList<Pair<Double, Double>>() {};
        percents.add(new Pair<Double, Double>(100.0, 0.01));
        CentralBank centralBank = CentralBank.GetInstance();
        Bank bank = centralBank.AddBank(10000, 10000, new PercentInfo(0.1, 0.15, percents));
        Person person = centralBank.AddPerson("Ivan", "Ivanov", bank);
        person.set_address("Nevskiy");
        person.set_passport(123);
        double cashBeforeReplenishment = 100000;
        GeneralCard debitCard = centralBank.AddDebitCard(person.get_personId(), cashBeforeReplenishment, bank);
        double cashForReplenishment = 10000;
        centralBank.Replenishment(debitCard.GetCardId(), cashForReplenishment);
        Assertions.assertEquals(debitCard.GetCash(), cashBeforeReplenishment + cashForReplenishment);
    }

    @Test
    void withdrawal() throws BanksException {
        List<Pair<Double, Double>> percents = new ArrayList<Pair<Double, Double>>() {};
        percents.add(new Pair<Double, Double>(100.0, 0.01));
        CentralBank centralBank = CentralBank.GetInstance();
        Bank bank = centralBank.AddBank(10000, 10000, new PercentInfo(0.1, 0.15, percents));
        Person person = centralBank.AddPerson("Ivan", "Ivanov", bank);
        person.set_address("Nevskiy");
        person.set_passport(123);
        double cashBeforeWithdrawal = 100000;
        GeneralCard debitCard = centralBank.AddDebitCard(person.get_personId(), cashBeforeWithdrawal, bank);
        double cashForWithdrawal = 10000;
        centralBank.Withdrawal(debitCard.GetCardId(), cashForWithdrawal);
        Assertions.assertEquals(debitCard.GetCash(), cashBeforeWithdrawal - cashForWithdrawal);
    }

    @Test
    void transfer() throws BanksException {
        List<Pair<Double, Double>> percents = new ArrayList<Pair<Double, Double>>() {};
        percents.add(new Pair<Double, Double>(100.0, 0.01));
        CentralBank centralBank = CentralBank.GetInstance();
        Bank bank = centralBank.AddBank(10000, 10000, new PercentInfo(0.1, 0.15, percents));
        Person person = centralBank.AddPerson("Ivan", "Ivanov", bank);
        person.set_address("Nevskiy");
        person.set_passport(123);
        double cashBeforeTransfer = 100000;
        GeneralCard debitCard1 = centralBank.AddDebitCard(person.get_personId(), cashBeforeTransfer, bank);
        GeneralCard debitCard2 = centralBank.AddDebitCard(person.get_personId(), cashBeforeTransfer, bank);
        double cashForTransfer = 10000;
        centralBank.Transfer(debitCard1.GetCardId(), debitCard2.GetCardId(), cashForTransfer);
        Assertions.assertEquals(debitCard1.GetCash(), cashBeforeTransfer - cashForTransfer);
        Assertions.assertEquals(debitCard2.GetCash(), cashBeforeTransfer + cashForTransfer);
    }

    @Test
    void changeDate() throws BanksException {
        List<Pair<Double, Double>> percents = new ArrayList<Pair<Double, Double>>() {};
        percents.add(new Pair<Double, Double>(100.0, 0.01));
        CentralBank centralBank = CentralBank.GetInstance();
        Bank bank = centralBank.AddBank(10000, 10000, new PercentInfo(0.1, 0.15, percents));
        Person person = centralBank.AddPerson("Ivan", "Ivanov", bank);
        person.set_address("Nevskiy");
        person.set_passport(123);
        double cashBeforeChangeDate = 100000;
        GeneralCard debitCard = centralBank.AddDebitCard(person.get_personId(), cashBeforeChangeDate, bank);
        GregorianCalendar newDate = new GregorianCalendar(2025, 1, 26);
        centralBank.ChangeDate(newDate);
        Assertions.assertEquals(debitCard.GetDate(), newDate);
        Assertions.assertNotEquals(debitCard.GetCash(), cashBeforeChangeDate);
    }
}